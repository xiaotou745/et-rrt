package com.renrentui.renrenapi.service.inter;


import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.MyReceiveTask;
import com.renrentui.renrenentity.domain.ReceiveNum;
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
import java.util.Map;

import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.SaveTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
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
	 TaskDetail getTaskDetail(TaskDatumDetailReq req);
	
	
	 OrderRetrunModel getTask(TaskDatumDetailReq req);
	
//	/**
//	 * 取消任务
//	 * @param req
//	 * @return
//	 */
//	 CancelTaskCode cancelTask(CancelTaskReq req);
	/**
	 * 提交任务
	 * 茹化肖
	 * 2015年10月8日10:52:52
	 * @param req
	 * @return
	 */
	 SubmitTaskCode submitTask(SubmitTaskReq req);
	 int insert(SaveTaskReq taskreq,List<Integer> regionCodes,List<Attachment> attachments);
	/**
	 * 修改任务 V1.0.2删除
	 * 茹化肖
	 * @param req
	 * @return
	 */
	 // int updateTask(RenRenTask record,List<Integer> regionCodes,List<Attachment> attachments);
	PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(PagedRenRenTaskReq req);	
	 int setTaskStatus(UpdateStatusReq req);
	
	 List<TaskModel> getNewTaskList(TaskReq req); 
	 int getNewTaskTotal(TaskReq req); 

	 List<MyReceiveTask> getMyReceivedTaskList(TaskReq req); 
	 ReceiveNum getMyReceivedTaskListTotal(TaskReq req);

	 List<TaskModel> getSubmittedTaskList(TaskReq req); 
	 int getSubmittedTaskListTotal(TaskReq req);
	
	/**
	 * 超时取消任务服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	 void outTimeCanelTask();
	 RenRenTask getTaskInfo(Long taskId);
	 List<RenRenTask> getListByTemplateId(Long templateId); 
//	 int settlementTask(Long taskId,String userName);
	 /**
		 * 获取任务控件信息
		 * 茹化肖
		 * 2015年11月25日13:38:41
		 */
	 List<TemplateGroup> getTemplateGroups(Long taskId);
	 /**
	  * 获取任务步骤信息
	  * @param taskId
	  * @return
	  */
	 ArrayList<TaskSetp> getTaskSetps(Long taskId);
	
}
