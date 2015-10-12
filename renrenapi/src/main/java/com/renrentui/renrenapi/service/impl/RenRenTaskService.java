package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.impl.RenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.IAttachmentDao;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IOrderChildDao;
import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenapi.dao.inter.IOrderLogDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskLogDao;
import com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailSnapshotDao;
import com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.enums.SubmitTaskCode;
import com.renrentui.renrencore.enums.TaskOpType;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.ClienterLog;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.OrderChild;
import com.renrentui.renrenentity.OrderLog;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.RenRenTaskLog;
import com.renrentui.renrenentity.TaskCityRelation;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.TemplateSnapshot;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.domain.CheckSubmitTask;
import com.renrentui.renrenentity.domain.OrderRetrunModel;
import com.renrentui.renrenentity.domain.RenRenTaskDetail;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;
import com.renrentui.renrenentity.req.TemplateSnapshotReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;
@Service
public class RenRenTaskService implements IRenRenTaskService{
	@Autowired
	private IRenRenTaskDao renRenTaskDao;	
	@Autowired
	private ITemplateDao templateDao;
	@Autowired
	private ITemplateDetailDao templateDetailDao;
	@Autowired
	private ITemplateSnapshotDao templateSnapshotDao;
	@Autowired
	private ITemplateDetailSnapshotDao templateDetailSnapshotDao;	
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
	
	@Autowired
	private IAttachmentDao attachmentDao;
	
	@Autowired 
	private IRenRenTaskLogDao renRenTaskLogDao;

	/**
	 * 获取任务详情
	 * 茹化肖
	 * 2015年9月29日13:00:35
	 */
	@Override
	public TaskDetail getTaskDetail(TaskDetailReq req) {
		
		TaskDetail detail=renRenTaskDao.getTaskDetail(req);//任务信息
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
		CheckTask detail=renRenTaskDao.checkTask(req);//获取任务相关数据
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
		int rescut=renRenTaskDao.cutTaskAvailableCount(req.getTaskId());//扣减任务量
		
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
		
		int addres=renRenTaskDao.addTaskAvailableCount(check.getTaskId());//增加任务的剩余量
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
			child.setTemplateSnapshotId(req.getTemplateId());
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
	public int insert(RenRenTask record,List<Integer> regionCodes,List<Attachment> attachments) {
		//一：将任务的模板的数据复制到模板快照表
		TemplateSnapshotReq req=new TemplateSnapshotReq();
		req.setTemplateId(record.getSnapshotTemplateId());
		int snapshotResult=templateSnapshotDao.copySnapshot(req);
		if (snapshotResult>0) {
			int detailSnapshotResult=templateDetailSnapshotDao.copySnapshot(record.getSnapshotTemplateId(), req.getTemplateSnapshotId());
			if (detailSnapshotResult>0) {
				//二：将任务的模板id设置为模板快照表的id，保存任务
				record.setSnapshotTemplateId(req.getTemplateSnapshotId());
				int result =renRenTaskDao.insert(record);
				if (result>0) {
					//三：保存任务的投放区域信息
					List<TaskCityRelation> recordList=getCityRelationList(record,regionCodes);
					int relationResult= taskCityRelationDao.insertList(recordList);
					if (relationResult>0){
						//三：保存任务的附件信息
						if(attachments!=null&&attachments.size()>0) {
							for (Attachment attachment : attachments) {
								attachment.setTaskId(record.getId());
								attachment.setBusinessId(record.getBusinessId());
							}
							attachmentDao.insertList(attachments);
						}
						//四：记录任务的操作日志
						RenRenTaskLog logRecord=new RenRenTaskLog();
						logRecord.setRenrenTaskId(record.getId());
						logRecord.setOptName(record.getCreateName());
						logRecord.setOptType((short)TaskOpType.NewTask.value());
						logRecord.setRemark(TaskOpType.NewTask.desc());
						renRenTaskLogDao.insert(logRecord);
					}
				}
			}
		}
		return snapshotResult;
	}
	@Override
	public PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(
			PagedRenRenTaskReq req) {
		return renRenTaskDao.getPagedRenRenTaskList(req);
	}
	@Override
	public int setTaskStatus(UpdateStatusReq req) {
		int result= renRenTaskDao.setTaskStatus(req);
		RenRenTaskLog logRecord=new RenRenTaskLog();
		logRecord.setRenrenTaskId(req.getReocrdId());
		logRecord.setOptName(req.getUserName());
		TaskStatus status=TaskStatus.getEnum(req.getStatus());
		TaskOpType opType=TaskOpType.NewTask;
		if (status==TaskStatus.Audited) {
			opType=TaskOpType.Audited;
		}else if (status==TaskStatus.Reject) {
			opType=TaskOpType.Reject;
		}else if (status==TaskStatus.Stop) {
			opType=TaskOpType.Stop;
		}

		logRecord.setOptType((short)opType.value());
		logRecord.setRemark(opType.desc());
		renRenTaskLogDao.insert(logRecord);
		return result;
	}
	@Override
	public List<TaskModel> getNewTaskList(TaskReq req) { 
		return renRenTaskDao.getNewTaskList(req);
	}
	@Override
	public int getNewTaskTotal(TaskReq req) {
		return renRenTaskDao.getNewTaskTotal(req);
	}
	@Override
	public List<TaskModel> getMyReceivedTaskList(TaskReq req) {
		return renRenTaskDao.getMyReceivedTaskList(req);
	}
	@Override
	public int getMyReceivedTaskListTotal(TaskReq req) {
		return renRenTaskDao.getMyReceivedTaskListTotal(req);
	}
	@Override
	public List<TaskModel> getSubmittedTaskList(TaskReq req) {
		return renRenTaskDao.getSubmittedTaskList(req);
	}
	@Override
	public int getSubmittedTaskListTotal(TaskReq req) {
		return renRenTaskDao.getSubmittedTaskListTotal(req);
	}

	/**
	 * 超时取消任务服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	@Override
	public void outTimeCanelTask() {
		renRenTaskDao.outTimeCanelTask();
	}
	@Override
	public RenRenTaskDetail getTaskInfo(Long taskId) {
		RenRenTaskDetail detail=null;
		RenRenTask model=renRenTaskDao.selectById(taskId);
		if (model!=null) {
		    detail=new RenRenTaskDetail();
			List<Attachment> attachList=attachmentDao.selectByTaskId(taskId);
			List<TaskCityRelation> relations=taskCityRelationDao.selectByTaskId(taskId);
			detail.setTaskInfo(model);
			detail.setAttachmentsList(attachList);
			detail.setCityRelationList(relations);
		}
		return detail;
	}
	private List<TaskCityRelation> getCityRelationList(RenRenTask record,List<Integer> regionCodes){
		Map<Integer,String> regionMap=publicProvinceCityService.getOpenCityMap();
		List<TaskCityRelation> recordList=new ArrayList<TaskCityRelation>();
		for (Integer regionCode : regionCodes) {
			TaskCityRelation taskCityRelation=new TaskCityRelation();
			taskCityRelation.setTaskId(record.getId());
			taskCityRelation.setCityCode(regionCode);
			taskCityRelation.setCityName("");
			if (regionMap.containsKey(regionCode)) {
				taskCityRelation.setCityName(regionMap.get(regionCode));
			}
			recordList.add(taskCityRelation);
		}
		return recordList;
	}
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int updateTask(RenRenTask record,List<Integer> regionCodes,List<Attachment> attachments){
		return 0;
//		RenRenTask model=renRenTaskDao.selectById(record.getId());
//		if (model==null) {
//			return -1;
//		}
//		StringBuilder sb=new StringBuilder();
//		sb.append(getUpdateRemark(record,model));
//		
//		List<Attachment> attachList=attachmentDao.selectByTaskId(record.getId());
//		List<TaskCityRelation> relations=taskCityRelationDao.selectByTaskId(record.getId());
//		//判断当前任务的模板快照和现在的模板数据是否一致，如果不一致，则需要重新生成模板快照
//		updateTemplateSnapshot(record,model);
//		if(attachments!=null&&attachments.size()>0) {
//			for (Attachment attachment : attachments) {
//				attachment.setTaskId(record.getId());
//				attachment.setBusinessId(record.getBusinessId());
//			}
//		}
//		List<TaskCityRelation> recordList=getCityRelationList(record,regionCodes);
//		RenRenTaskLog logRecord=new RenRenTaskLog();
//		logRecord.setRenrenTaskId(record.getId());
//		logRecord.setOptName(record.getModifyName());
//		logRecord.setOptType((short)TaskOpType.Modify.value());
//		logRecord.setRemark(TaskOpType.Modify.desc());
//		renRenTaskLogDao.insert(logRecord);
//		return 0;
	}
	private void updateTemplateSnapshot(RenRenTask record,RenRenTask oldTaskmodel){
		boolean needReCreateSnapshot=false;
		TemplateSnapshot snapshotTemplate=templateSnapshotDao.detailByTemplateId(record.getSnapshotTemplateId());
		if(oldTaskmodel.getSnapshotTemplateId()==record.getSnapshotTemplateId()){
			Template nowTemplate=templateDao.detail(record.getSnapshotTemplateId());
			if (nowTemplate.getLastOptTime()!=snapshotTemplate.getLastOptTime()) {
				needReCreateSnapshot=true;
			}
		}else {
			needReCreateSnapshot=true;
		}
		if (needReCreateSnapshot) {
			templateSnapshotDao.deleteByTemplateId(record.getSnapshotTemplateId());
			templateDetailSnapshotDao.deleteBySnapshotTemplateId(snapshotTemplate.getId());
			TemplateSnapshotReq req=new TemplateSnapshotReq();
			req.setTemplateId(record.getSnapshotTemplateId());
			int snapshotResult=templateSnapshotDao.copySnapshot(req);
			if (snapshotResult==0) {
				throw new RuntimeException("生成任务的模板快照失败");
			}
			int detailSnapshotResult=templateDetailSnapshotDao.copySnapshot(record.getSnapshotTemplateId(), req.getTemplateSnapshotId());
			if (detailSnapshotResult==0) {
				throw new RuntimeException("生成任务的模板详情快照失败");
			}
		}
	}
	private String getUpdateRemark(RenRenTask record,RenRenTask oldModel){
		if (oldModel==null||record==null) {
			return "";
		}
		StringBuilder sb=new StringBuilder();
		if (record.getTaskTitle().equals(oldModel.getTaskTitle())) {
			sb.append("任务标题从:"+record.getTaskTitle()+"改为了:"+oldModel.getTaskTitle()+";");
		}

		return sb.toString();
	}
}
