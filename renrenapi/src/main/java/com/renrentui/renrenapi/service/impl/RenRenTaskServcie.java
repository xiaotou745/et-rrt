package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenapi.service.inter.IRenRenTaskServcie;
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

}
