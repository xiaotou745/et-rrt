package com.renrentui.renrenapi.service.inter;


import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrencore.enums.SubmitTaskCode;
import com.renrentui.renrenentity.domain.OrderRetrunModel;
import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.RenRenTask;

import java.util.List;

import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;

public interface IRenRenTaskService {

	/**
	 * 获取任务详情
	 * 2015年9月29日13:00:11
	 * 茹化肖
	 * @param req
	 * @return
	 */
	public TaskDetail getTaskDetail(TaskDetailReq req);
	
	
	public OrderRetrunModel getTask(TaskDetailReq req);
	
	/**
	 * 取消任务
	 * @param req
	 * @return
	 */
	public CancelTaskCode cancelTask(CancelTaskReq req);
	/**
	 * 提交任务
	 * 茹化肖
	 * 2015年10月8日10:52:52
	 * @param req
	 * @return
	 */
	public SubmitTaskCode submitTask(SubmitTaskReq req);
	public int insert(RenRenTask record,List<Integer> regionCodes,List<Attachment> attachments);
	
	PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(PagedRenRenTaskReq req);	
	public int setTaskStatus(long taskID,int status);
	
	public List<TaskModel> getNewTaskList(TaskReq req); 
	public int getNewTaskTotal(TaskReq req); 

	public List<TaskModel> getMyReceivedTaskList(TaskReq req); 
	public int getMyReceivedTaskListTotal(TaskReq req);
}
