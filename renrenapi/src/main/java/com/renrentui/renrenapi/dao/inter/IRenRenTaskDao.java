package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.ClienterTask;
import com.renrentui.renrenentity.domain.MyReceiveTask;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemplateGroup;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
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
    RenRenTask getTaskDetail(TaskDatumDetailReq req);
    
    CheckTask checkTask(TaskDatumDetailReq req);
    
    int cutTaskAvailableCount(Long taskID);
    int addTaskAvailableCount(Long taskID);
	PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(PagedRenRenTaskReq req);	
	public int setTaskStatus(UpdateStatusReq req);
	List<TaskModel> getNewTaskList(TaskReq req);

	int getNewTaskTotal(TaskReq req);

	List<MyReceiveTask> getMyReceivedTaskList(TaskReq req);

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
	/**
	 * 插入客户任务关系(领取任务)
	 * 茹化肖
	 * 2015年11月19日14:28:27
	 * V1.0.2
	 * @param task
	 * @return
	 */
	int insertClienterTask(ClienterTask task);
	/**
	 * 提交资料将完成数量加1
	 * @param ctId
	 * @return
	 */
	int addClienterCompleteCount(Long ctId);
	/**
	 * 清除任务下的步骤,控件,控件组,投放城市
	 * 茹化肖
	 * 2015年11月26日16:57:32
	 * @param taskId
	 * @return
	 */
	int clearTaskInfo(Long taskId);
	/**
	 * 修改任务更新任务信息
	 * 茹化肖
	 * 2015年11月26日16:58:14
	 * @param task
	 * @return
	 */
	int updateTaskInfo(RenRenTask task);
}