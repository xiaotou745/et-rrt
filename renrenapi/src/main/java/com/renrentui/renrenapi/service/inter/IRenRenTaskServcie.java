package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.domain.TaskDetail;
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
}
