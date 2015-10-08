package com.renrentui.renrenapihttp.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskDomain;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;

/**
 * 任务相关模块
 * @author 茹化肖
 * @date 2015年9月28日09:55:18
 */
@Path("/task")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface ITaskService {
	
	/**
	 * 任务详情接口
	 * @author 茹化肖
	 * @date 2015年9月28日10:18:57
	 * @return
	 */
	@POST
	@Path("/taskdetail")
	 public HttpResultModel<TaskDetail> taskDeatil(TaskDetailReq req);
	
	/**
	 * 领取任务接口
	 * @author 茹化肖
	 * @date 2015年9月28日10:18:57
	 * @return
	 */
	@POST
	@Path("/gettask")
	 public HttpResultModel<Object> getTask(TaskDetailReq req);
	/**
	 * 取消任务接口
	 * @author 茹化肖
	 * @date 2015年9月28日10:18:57
	 * @return
	 */
	@POST
	@Path("/canceltask")
	 public HttpResultModel<Object> cancelTask(CancelTaskReq req);
	
	/**
	 * 提交任务接口
	 * @author 茹化肖
	 * @date 2015年9月28日10:18:57
	 * @return
	 */
	@POST
	@Path("/submittask")
	 public HttpResultModel<Object> submitTask(SubmitTaskReq req);

	/*
	 * 获取所有未领取的任务
	 * wangchao
	 */
	@POST
	@Path("/getnewtasklist")
	public HttpResultModel<TaskDomain> getNewTaskList(TaskReq req);
	
	/*
	 * 获取所有已领取的任务
	 * wangchao
	 */
	@POST
	@Path("/getmyreceivedtasklist")
	public HttpResultModel<TaskDomain> getMyReceivedTaskList(TaskReq req);
	
	
	/*
	 * 获取所有已提交的任务
	 * wangchao
	 */
	@POST
	@Path("/getsubmittedtasklist")
	public HttpResultModel<TaskDomain> getSubmittedTaskList(TaskReq req);
	
}
