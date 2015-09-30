package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenapi.service.inter.IRenRenTaskServcie;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.TaskDetailReq;
@Service
public class RenRenTaskServcie implements IRenRenTaskServcie{
	@Autowired
	private IRenRenTaskDao rereRenTaskDao;	
	@Autowired
	private ITemplateDetailDao templateDetailDao;	
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
		return null;
	}

}
