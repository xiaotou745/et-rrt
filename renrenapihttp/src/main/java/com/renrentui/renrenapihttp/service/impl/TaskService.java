package com.renrentui.renrenapihttp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.ITaskService;
import com.renrentui.renrencore.cache.redis.RedisService;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.enums.TaskDetailCode;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.TaskDetailReq;

/**
 * 任务相关
 * 
 * @author 茹化肖
 * @date 2015年9月28日09:59:42
 */
@Service
public class TaskService implements ITaskService{
//	@Autowired
//	IClienterService clienterService;
//	
//	@Autowired
//	private IClienterBalanceService clienterBalanceService;	
//
	@Autowired
	IRenRenTaskService rrTaskServcie;
	/**
	 * C端任务详情接口
	 */
	@Override
	public HttpResultModel<TaskDetail> taskDeatil(TaskDetailReq req) {
		if(req.getTaskId()<=0)//任务ID
			return new HttpResultModel<TaskDetail>().setCode(TaskDetailCode.TaskIdErr.value()).setMsg(TaskDetailCode.TaskIdErr.desc());
		if(req.getUserId()<=0)//用户ID
			return new HttpResultModel<TaskDetail>().setCode(TaskDetailCode.UserIdErr.value()).setMsg(TaskDetailCode.UserIdErr.desc());
		TaskDetail detail= rrTaskServcie.getTaskDetail(req);
		if(detail==null)
			return new HttpResultModel<TaskDetail>().setCode(TaskDetailCode.Fail.value()).setMsg(TaskDetailCode.Fail.desc());
		return new HttpResultModel<TaskDetail>().setData(detail).setCode(TaskDetailCode.Success.value()).setMsg(TaskDetailCode.Success.desc());
	}
	/**
	 * 领取任务接口
	 * 茹化肖
	 * 2015年9月29日15:40:50
	 * 
	 */
	@Override
	public HttpResultModel<Object> getTask(TaskDetailReq req) {
		if(req.getTaskId()<=0)//任务ID
			return new HttpResultModel<Object>().setCode(GetTaskCode.TaskIdErr.value()).setMsg(GetTaskCode.TaskIdErr.desc());
		if(req.getUserId()<=0)//用户ID
			return new HttpResultModel<Object>().setCode(GetTaskCode.UserIdErr.value()).setMsg(GetTaskCode.UserIdErr.desc());
		if(1==1)
			return new HttpResultModel<Object>().setCode(GetTaskCode.Success.value()).setMsg(GetTaskCode.Success.desc());
		return null;
	}
}
