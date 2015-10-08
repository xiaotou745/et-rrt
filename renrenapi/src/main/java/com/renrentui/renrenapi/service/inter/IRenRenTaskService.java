package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.TaskCityRelation;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.TaskDetailReq;

public interface IRenRenTaskService {

	/**
	 * 获取任务详情
	 * 2015年9月29日13:00:11
	 * 茹化肖
	 * @param req
	 * @return
	 */
	public TaskDetail getTaskDetail(TaskDetailReq req);
	
	
	public GetTaskCode getTask(TaskDetailReq req);
	public int insert(RenRenTask record,List<Integer> regionCodes);
}
