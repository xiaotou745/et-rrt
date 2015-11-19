package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.MyJobTaskDomain;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemplateGroup;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;

public interface IRenRenTaskDao {

    int insert(RenRenTask record);
    int update(RenRenTask record);
    RenRenTask selectById(Long id);
    /**
     * 获取任务详情(接口用)
     * 茹化肖 1.0.2
     * 修改时间 2015年11月19日11:23:44
     * @param req
     * @return
     */
    RenRenTask getTaskDetail(TaskDetailReq req);
    
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
	public List<RenRenTask> getListByTemplateId(Long templateId);
	/**
	 * 统计我的任务列表   已领取 审核中 未通过 -的数量信息  add by caoheyang  20151026
	 * @param req
	 * @return
	 */
	MyJobTaskDomain getMyJobCount(TaskReq req); 
	/**
	 * 插入步骤信息
	 * 茹化肖
	 * 2015年11月16日15:27:05
	 * @param setp
	 * @return
	 */
	int insertTaskSetp(TaskSetp setp);
	/**
	 * 插入模板组信息
	 * 茹化肖
	 * 2015年11月16日15:42:59
	 * @param group
	 * @return
	 */
	int insertTemplateGrpup(TemplateGroup group);
}