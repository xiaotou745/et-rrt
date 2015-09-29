package com.renrentui.renrenapihttp.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.TaskDetailReq;

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

}
