package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;

public interface IRenRenTaskDao {

    int insert(RenRenTask record);

    RenRenTask selectById(Long id);
    
    TaskDetail getTaskDetail(TaskDetailReq req);
    
    CheckTask checkTask(TaskDetailReq req);
    
    int cutTaskAvailableCount(Long taskID);
    int addTaskAvailableCount(Long taskID);
	PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(PagedRenRenTaskReq req);	
	public int setTaskStatus(UpdateStatusReq req);
	List<TaskModel> getNewTaskList(TaskReq req);

	int getNewTaskTotal(TaskReq req);

	List<TaskModel> getMyReceivedTaskList(TaskReq req);

	int getMyReceivedTaskListTotal(TaskReq req);

	List<TaskModel> getSubmittedTaskList(TaskReq req);

	int getSubmittedTaskListTotal(TaskReq req);
	
	/**
	 * 超时取消任务服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	public void outTimeCanelTask();
}