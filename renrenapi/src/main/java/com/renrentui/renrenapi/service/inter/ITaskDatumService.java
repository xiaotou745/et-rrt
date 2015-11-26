package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.req.TaskDatumReq;


public interface ITaskDatumService {
	// List<TaskDetailReq> getTaskDetail(TaskDatumReq req);
	/**
	 * 获取我的资料列表
	 * @author hailongzhao
	 * @date 20151125
	 * @param req
	 * @return
	 */
	 List<TaskDatumModel> getMyTaskDatumList(TaskDatumReq req);
}
