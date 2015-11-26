package com.renrentui.renrenapihttp.service.impl;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.ITaskService;
import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.enums.SubmitTaskCode;
import com.renrentui.renrencore.enums.TaskCode;
import com.renrentui.renrencore.enums.TaskDetailCode;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrenentity.domain.MyReceiveTask;
import com.renrentui.renrenentity.domain.OrderRetrunModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TabModel;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.domain.TemplateInfo;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;

/**
 * 任务相关
 * 
 * @author 茹化肖
 * @date 2015年9月28日09:59:42
 */
@Service
public class TaskService implements ITaskService{
	@Autowired
	IRenRenTaskService rrTaskServcie;
	/**
	 * C端任务详情接口
	 */
	@Override
	public HttpResultModel<TaskDetail> taskDeatil(TaskDetailReq req) {
		if(req.getTaskId()<=0)//任务ID
			return new HttpResultModel<TaskDetail>().setCode(TaskDetailCode.TaskIdErr.value()).setMsg(TaskDetailCode.TaskIdErr.desc());
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
		if(req.getTaskId()==null||req.getTaskId()<=0)//任务ID
			return new HttpResultModel<Object>().setCode(GetTaskCode.TaskIdErr.value()).setMsg(GetTaskCode.TaskIdErr.desc());
		if(req.getUserId()==null||req.getUserId()<=0)//用户ID
			return new HttpResultModel<Object>().setCode(GetTaskCode.UserIdErr.value()).setMsg(GetTaskCode.UserIdErr.desc());
		OrderRetrunModel code=rrTaskServcie.getTask(req);//领取任务
		return new HttpResultModel<Object>().setCode(code.getCode().value()).setMsg(code.getCode().desc());
	}
	/**
	 * 取消任务接口
	 */
	@Override
	public HttpResultModel<Object> cancelTask(CancelTaskReq req) {
		if(req.getOrderId()==null||req.getOrderId()<=0)
			return new HttpResultModel<Object>().setCode(CancelTaskCode.OrderIdErr.value()).setMsg(CancelTaskCode.OrderIdErr.desc());
		if(req.getUserId()==null||req.getUserId()<=0)
			return new HttpResultModel<Object>().setCode(CancelTaskCode.UserIdErr.value()).setMsg(CancelTaskCode.UserIdErr.desc());
		CancelTaskCode code=rrTaskServcie.cancelTask(req);
		return new HttpResultModel<Object>().setCode(code.value()).setMsg(code.desc());
	}
	/**
	 * 提交任务
	 * 茹化肖
	 * 2015年9月30日14:53:05
	 */
	@Override
	public HttpResultModel<Object> submitTask(SubmitTaskReq req) {
		if(req.getUserId()==null||req.getUserId()==0)
			return new HttpResultModel<Object>().setCode(SubmitTaskCode.UserIdError.value()).setMsg(SubmitTaskCode.UserIdError.desc());
		if(req.getTaskId()==null||req.getTaskId()==0)
			return new HttpResultModel<Object>().setCode(SubmitTaskCode.OrderIdError.value()).setMsg(SubmitTaskCode.OrderIdError.desc());
		SubmitTaskCode code=rrTaskServcie.submitTask(req);
		return new HttpResultModel<Object>().setCode(code.value()).setMsg(code.desc());
	}
/**
 * 获取可接任务列表
 * @author hailongzhao
 * @date 20151125 
 */
	@Override
	public HttpResultModel<TabModel<TaskModel>> getNewTaskList(TaskReq req) {
		HttpResultModel<TabModel<TaskModel>> hrm = new HttpResultModel<TabModel<TaskModel>>();
		hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
		if(req.getUserId()<0){
			hrm.setCode(TaskCode.UserIdErr.value()).setMsg(TaskCode.UserIdErr.desc());			
			return hrm;
		} 
		if(req.getCityCode()<=0){
			hrm.setCode(TaskCode.CityCode.value()).setMsg(TaskCode.CityCode.desc());			
			return hrm;
		}  
		List<TaskModel> taskModelList= rrTaskServcie.getNewTaskList(req);
		//int taskTotal = rrTaskServcie.getNewTaskTotal(req);
		TabModel<TaskModel> td = new TabModel<TaskModel>();
		td.setContent(taskModelList);
		td.setCount(taskModelList.size());
		if(taskModelList!=null && taskModelList.size()>0){
			td.setNextId(taskModelList.get(taskModelList.size()-1).getTaskId());
		}
		hrm.setData(td); 
		return hrm;
	}
	/**
	 * 获取我的任务列表
	 * @author hailongzhao
	 * @date 20151125
	 */
	@Override
	public HttpResultModel<TabModel<MyReceiveTask>> getMyReceivedTaskList(TaskReq req) {
		HttpResultModel<TabModel<MyReceiveTask>> hrm = new HttpResultModel<TabModel<MyReceiveTask>>();
		hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
		if(req.getUserId()<=0){
			hrm.setCode(TaskCode.UserIdErr.value()).setMsg(TaskCode.UserIdErr.desc());			
			return hrm;
		} 
		if(req.getCityCode()<=0){
			hrm.setCode(TaskCode.CityCode.value()).setMsg(TaskCode.CityCode.desc());			
			return hrm;
		} 
		if(req.getTaskStatus()!=TaskStatus.Audited.value()&&
		   req.getTaskStatus()!=TaskStatus.Expired.value()){
			hrm.setCode(TaskCode.TaskStatus.value()).setMsg(TaskCode.TaskStatus.desc());			
			return hrm;
		} 
		List<MyReceiveTask> taskModelList= rrTaskServcie.getMyReceivedTaskList(req);
		TabModel<MyReceiveTask> td = new TabModel<MyReceiveTask>();
		td.setContent(taskModelList);
		td.setCount(taskModelList.size());
		if (req.getTaskStatus()==TaskStatus.Audited.value()) {
			td.setTitle("进行中("+td.getCount()+")");
		}
		else if(req.getTaskStatus()==TaskStatus.Expired.value()){
			td.setTitle("已过期("+td.getCount()+")");
		}
		if(taskModelList!=null && taskModelList.size()>0){
			td.setNextId(taskModelList.get(taskModelList.size()-1).getTaskId());
		}
		hrm.setData(td); 
		return hrm;
	}
	
	/**
	 * 提交资料的详情接口
	 * 茹化肖
	 * 2015年11月19日15:41:43
	 * V1.0.2
	 */
	@Override
	public HttpResultModel<TemplateInfo> templateDeatil(TaskDetailReq req) {
		TemplateInfo detail= rrTaskServcie.getTemplateDetail(req);
		if(detail==null)
			return new HttpResultModel<TemplateInfo>().setData(detail).setCode(TaskDetailCode.Fail.value()).setMsg(TaskDetailCode.Fail.desc())  ;
		return new HttpResultModel<TemplateInfo>().setData(detail).setCode(TaskDetailCode.Success.value()).setMsg(TaskDetailCode.Success.desc()) ;
	}
}
