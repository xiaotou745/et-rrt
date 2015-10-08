package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;

public interface IRenRenTaskServcie {

	/**
	 * 获取任务详情
	 * 2015年9月29日13:00:11
	 * 茹化肖
	 * @param req
	 * @return
	 */
	public TaskDetail getTaskDetail(TaskDetailReq req);
	
	
	public GetTaskCode getTask(TaskDetailReq req);


	public List<TaskModel> getNewTaskList(TaskReq req);


	public int getNewTaskTotal(TaskReq req);
}
