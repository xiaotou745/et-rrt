package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.enums.SubmitTaskCode;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;

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
	
	/**
	 * 取消任务
	 * @param req
	 * @return
	 */
	public CancelTaskCode cancelTask(CancelTaskReq req);
	
	public SubmitTaskCode submitTask(SubmitTaskReq req);
}
