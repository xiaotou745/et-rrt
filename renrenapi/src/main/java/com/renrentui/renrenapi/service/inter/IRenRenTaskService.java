package com.renrentui.renrenapi.service.inter;


import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.MyJobTaskDomain;
import com.renrentui.renrenentity.domain.RenRenTaskDetail;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemplateGroup;
import com.renrentui.renrenentity.domain.TemplateInfo;
import com.renrentui.renrencore.enums.SubmitTaskCode;
import com.renrentui.renrenentity.domain.OrderRetrunModel;
import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.RenRenTask;






import java.util.ArrayList;
import java.util.List;

import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.SaveTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;

public interface IRenRenTaskService {

	/**
	 * 获取任务详情
	 * 2015年9月29日13:00:11
	 * 茹化肖
	 * @param req
	 * @return
	 */
	 TaskDetail getTaskDetail(TaskDetailReq req);
	
	
	 OrderRetrunModel getTask(TaskDetailReq req);
	
	/**
	 * 取消任务
	 * @param req
	 * @return
	 */
	 CancelTaskCode cancelTask(CancelTaskReq req);
	/**
	 * 提交任务
	 * 茹化肖
	 * 2015年10月8日10:52:52
	 * @param req
	 * @return
	 */
	 SubmitTaskCode submitTask(SubmitTaskReq req);
	 int insert(SaveTaskReq taskreq,List<Integer> regionCodes,List<Attachment> attachments);
	 int updateTask(RenRenTask record,List<Integer> regionCodes,List<Attachment> attachments);
	PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(PagedRenRenTaskReq req);	
	 int setTaskStatus(UpdateStatusReq req);
	
	 List<TaskModel> getNewTaskList(TaskReq req); 
	 int getNewTaskTotal(TaskReq req); 

	 List<TaskModel> getMyReceivedTaskList(TaskReq req); 
	 int getMyReceivedTaskListTotal(TaskReq req);


	 List<TaskModel> getSubmittedTaskList(TaskReq req); 
	 int getSubmittedTaskListTotal(TaskReq req);
	/**
	 * 统计我的任务列表   已领取 审核中 未通过 -的数量信息  add by caoheyang  20151026
	 * @param req
	 * @return
	 */
	  MyJobTaskDomain getMyJobCount(TaskReq req);
	
	/**
	 * 超时取消任务服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	 void outTimeCanelTask();
	 /**
	  * 获取任务信息(详情页)
	  * 茹化肖
	  * 2015年11月25日13:05:00
	  * @param taskId
	  * @return
	  */
	 RenRenTask getTaskInfo(Long taskId);
	 List<RenRenTask> getListByTemplateId(Long templateId); 
	 int settlementTask(Long taskId,String userName);
	 /**
	  * 提交资料详情
	  * 茹化肖
	  * 2015年11月19日15:48:05
	  * @param req
	  * @return
	  */
	 TemplateInfo getTemplateDetail(TaskDetailReq req);
	 /**
	  * 获取任务的步骤信息
	  * 茹化肖
	  * 2015年11月25日13:22:32
	  * @param taskId
	  * @return
	  */
	 ArrayList<TaskSetp> getTaskSetps(Long taskId);
	 /**
	  * 获取模板组级控件信息
	  * 茹化肖
	  * 2015年11月25日13:30:57
	  * 
	  * @param taskId
	  * @return
	  */
	 List<TemplateGroup> getTemplateGroups(Long taskId);
	
}
