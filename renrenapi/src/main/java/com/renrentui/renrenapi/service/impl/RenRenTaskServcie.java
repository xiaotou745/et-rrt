package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenapi.dao.inter.IOrderLogDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenapi.service.inter.IRenRenTaskServcie;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.ClienterLog;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.OrderLog;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.TaskDetailReq;
@Service
public class RenRenTaskServcie implements IRenRenTaskServcie{
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
	    detail.setControlInfo(templateDetailDao.getTemplateList(detail.getTempateId()));
		return detail;
	}
	/**
	 * 领取任务接口
	 * 茹化肖
	 * 2015年9月29日16:30:00
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public GetTaskCode getTask(TaskDetailReq req) {
		CheckTask detail=rereRenTaskDao.checkTask(req);//获取任务相关数据
		if(detail==null)//没有查询到任务相关信息
			return GetTaskCode.Fail;
		if(detail.getBlanceCan()==0)//任务余量不足领取
			return GetTaskCode.TaskNoBlance;
		if(detail.getEndTimeCan()==0)//任务过期关闭
			return GetTaskCode.TaskExpire;
		if(detail.getOrderCan()==0)//有未完成的任务
			return GetTaskCode.TaskHad;
		//领取任务 插入订单
		String orderNoString=OrderNoHelper.generateOrderCode(req.getUserId());//生成订单号
		Order order=new Order();
		order.setOrderNo(orderNoString);
		order.setClienterId(req.getUserId());
		order.setTaskId(req.getTaskId());
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
			return GetTaskCode.Success;
		}
		else {
			Error error=new Error("添加订单错误");
			throw new RuntimeErrorException(error);
		}
	}

}
