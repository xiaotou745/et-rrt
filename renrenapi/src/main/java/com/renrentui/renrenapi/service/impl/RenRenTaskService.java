package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IOrderChildDao;
import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenapi.dao.inter.IOrderLogDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.enums.SubmitTaskCode;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.ClienterLog;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.OrderChild;
import com.renrentui.renrenentity.OrderLog;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.TaskCityRelation;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.domain.CheckSubmitTask;
import com.renrentui.renrenentity.domain.OrderRetrunModel;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
@Service
public class RenRenTaskService implements IRenRenTaskService{
	@Autowired
	private IRenRenTaskDao rereRenTaskDao;	
	@Autowired
	private ITemplateDetailDao templateDetailDao;	
	@Autowired
	private IOrderDao orderDao;	
	@Autowired
	private IClienterLogDao clienterLogDao;	
	@Autowired
	private IOrderLogDao orderLogDao;
	
	@Autowired
	private IOrderChildDao orderChildDaoDao;
	@Autowired
	private ITaskCityRelationDao taskCityRelationDao;	
	@Autowired
	private IPublicProvinceCityService publicProvinceCityService;

	/**
	 * 获取任务详情
	 * 茹化肖
	 * 2015年9月29日13:00:35
	 */
	@Override
	public TaskDetail getTaskDetail(TaskDetailReq req) {
		
		TaskDetail detail=rereRenTaskDao.getTaskDetail(req);//任务信息
		if(detail==null)//没有找到任务信息
			return null;
		//控件列表
	    detail.setControlInfo(templateDetailDao.getTemplateList(detail.getTemplateId()));
		return detail;
	}
	/**
	 * 领取任务接口
	 * 茹化肖
	 * 2015年9月29日16:30:00
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public OrderRetrunModel getTask(TaskDetailReq req) {
		OrderRetrunModel model=new OrderRetrunModel();
		CheckTask detail=rereRenTaskDao.checkTask(req);//获取任务相关数据
		if(detail==null)//没有查询到任务相关信息
		{
			 model.setCode(GetTaskCode.Fail);
			 return model;
		}
		if(detail.getBlanceCan()==0)//任务余量不足领取
		{
			 model.setCode(GetTaskCode.TaskNoBlance);
			 return model;
		}
		if(detail.getEndTimeCan()==0)//任务过期关闭
		{
			 model.setCode(GetTaskCode.TaskExpire);
			 return model;
		}
		if(detail.getOrderCan()==0)//有未完成的任务
		{
			 model.setCode(GetTaskCode.TaskHad);
			 return model;
		}
		//领取任务 插入订单
		String orderNoString=OrderNoHelper.generateOrderCode(req.getUserId());//生成订单号
		Order order=new Order();
		order.setOrderNo(orderNoString);
		order.setClienterId(req.getUserId());
		order.setTaskId(req.getTaskId());
		order.setAmount(detail.getAmount());
		Date dealLineDate=ParseHelper.plusDate(new Date(), 3, (int)detail.getTaskCycle());
		System.out.println(dealLineDate);
		order.setDeadlineTime(dealLineDate);
		int res=orderDao.addOrder(order);//添加订单信息
		int rescut=rereRenTaskDao.cutTaskAvailableCount(req.getTaskId());//扣减任务量
		
		ClienterLog log=new ClienterLog();
		log.setClienterId(req.getUserId());
		log.setOptName("地推员ID");
		log.setRemark("地推员:"+req.getUserId()+"领取任务:"+req.getTaskId()+"订单号:"+orderNoString);
		int reslog=clienterLogDao.addClienterLog(log);//记录C端日志
		
		OrderLog orderLog=new OrderLog();
		orderLog.setOrderNo(orderNoString);
		orderLog.setOrderId(order.getId());
		orderLog.setOptType(Short.valueOf("1"));
		orderLog.setOptName("地推员:"+req.getUserId());
		orderLog.setRemark("地推员:"+req.getUserId()+"抢单:"+orderNoString);
		int orderlogres=orderLogDao.addOrderLog(orderLog);//记录订单操作日志
		if(res>0&&rescut>0&&reslog>0&&orderlogres>0){
			model.setOrderId(order.getId());
			model.setCode(GetTaskCode.Success);
			return model;
		}
		else {
			Error error=new Error("添加订单错误");
			throw new RuntimeErrorException(error);
		}
	}
	/**
	 * 取消任务 
	 * 茹化肖
	 * 2015年9月30日13:24:27
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public CancelTaskCode cancelTask(CancelTaskReq req) {
		//验证订单相关信息
		CheckCancelOrder check=orderDao.checkCancelOrder(req);
		if(check==null)//订单不存在
			return CancelTaskCode.OrderNull;
		if(check.getCancelCan()==0)//订单不能取消
		{
			if(check.getIsCancle()==1)//订单已经取消
				return CancelTaskCode.TaskIsCancel;
			if(check.getIsComplete()==1)//订单已经完成不能取消
				return CancelTaskCode.TaskComplete;
			return CancelTaskCode.CantCancel;//订单不可取消
		}
		
		int res=orderDao.cancelOrder(req);//取消订单
		
		OrderLog orderLog=new OrderLog();
		orderLog.setOrderNo(check.getOrderNo());
		orderLog.setOrderId(req.getOrderId());
		orderLog.setOptType(Short.valueOf("1"));
		orderLog.setOptName("地推员:"+req.getUserId());
		orderLog.setRemark("地推员:"+req.getUserId()+"取消订单:"+check.getOrderNo());
		int orderlogres=orderLogDao.addOrderLog(orderLog);//记录订单操作日志
		
		int addres=rereRenTaskDao.addTaskAvailableCount(check.getTaskId());//增加任务的剩余量
		if(res>0&&orderlogres>0&&addres>0){
			return CancelTaskCode.Success;
		}
		else {
			Error error=new Error("取消任务错误");
			throw new RuntimeErrorException(error);
		}
	}
	/**
	 * 提交任务
	 * 茹化肖
	 * 2015年10月8日13:37:08
	 */
	@Override
	public SubmitTaskCode submitTask(SubmitTaskReq req) {
		CheckSubmitTask check=orderDao.checkOrderSubmit(req);
		int deleInt=0;
		if(check==null)
			return SubmitTaskCode.CantSubmit;
		if(check.getSubmitCan()==0)//订单不可提交
		{
			if(check.getIsCancel()==1)//订单已经取消
				return SubmitTaskCode.OrderCancel;
			if(check.getTaskClosed()==1)//任务已关闭 且不是修改后提交
				return SubmitTaskCode.TaskClosed;
			if(check.getReSubmit()==1)//任务已经提交待审核 不可重复提交
				return SubmitTaskCode.ReSubmit;
			return SubmitTaskCode.CantSubmit;
		}
		OrderLog orderLog=new OrderLog();
		orderLog.setOrderNo("");
		orderLog.setOrderId(req.getOrderId());
		orderLog.setOptType(Short.valueOf("2"));
		orderLog.setOptName("地推员:"+req.getUserId());
		orderLog.setRemark("地推员:"+req.getUserId()+"提交订单ID:"+req.getOrderId());
		if(check.getSubmitCan()==1&&check.getIsAgainSubmit()==1)
		{
			//再次提交合同信息..删除之前提交的合同
			deleInt=orderChildDaoDao.deleteOrderChild(req.getOrderId());
			if(deleInt<=0)
			{
				//清除旧数据失败
				Error error=new Error("清除旧合同信息错误");
				throw new RuntimeErrorException(error);
			}
			orderLog.setOptType(Short.valueOf("3"));
			orderLog.setRemark("地推员:"+req.getUserId()+"审核拒绝后再次提交订单ID:"+req.getOrderId());
		}
		//更新订单状态00
		int resSubmit=orderDao.submitOrder(req);
		//插入订单操作记录
		int orderlogres=orderLogDao.addOrderLog(orderLog);//记录订单操作日志
		//插入子订单信息
		int childres=0;
		for (int i = 0; i < req.getValueInfo().size(); i++) {
			OrderChild child=new OrderChild();
			child.setOrderId(req.getOrderId());
			child.setControlName(req.getValueInfo().get(i).getControlName());
			child.setControlValue(req.getValueInfo().get(i).getControlValue());
			childres+=orderChildDaoDao.insert(child);
		}
		if(resSubmit>0&&orderlogres>0&&(childres==req.getValueInfo().size()))
		{
			//返回订单提交成功
			return SubmitTaskCode.Success;
		}
		else {
			//提交合同失败
			Error error=new Error("提交合同信息失败");
			throw new RuntimeErrorException(error);
		}
	}
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int insert(RenRenTask record,List<Integer> regionCodes) {
		int result =rereRenTaskDao.insert(record);
		if (result>0) {
			Map<Integer,String> regionMap=publicProvinceCityService.getOpenCityMap();
			List<TaskCityRelation> recordList=new ArrayList<TaskCityRelation>();
			for (Integer regionCode : regionCodes) {
				TaskCityRelation taskCityRelation=new TaskCityRelation();
				taskCityRelation.setTaskId(record.getId());
				taskCityRelation.setBusinessId(record.getBusinessId());
				taskCityRelation.setCityCode(regionCode);
				taskCityRelation.setCityName("");
				if (regionMap.containsKey(regionCode)) {
					taskCityRelation.setCityName(regionMap.get(regionCode));
				}
				recordList.add(taskCityRelation);
			}
			return taskCityRelationDao.insertList(recordList);
		}
		return result;
	}
	@Override
	public PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(
			PagedRenRenTaskReq req) {
		return rereRenTaskDao.getPagedRenRenTaskList(req);
	}
	@Override
	public int setTaskStatus(long taskID, int status) {
		return rereRenTaskDao.setTaskStatus(taskID, status);
	}

}
