package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.domain.TaskDatumDetailGroup;
import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.domain.TemplateInfo;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
import com.renrentui.renrenentity.req.TaskDatumReq;


public interface ITaskDatumService {
	/**
	 * 获取资料模板或资料详情
	 * @param req
	 * @author hailongzhao
	 * @date 20151126
	 * @return
	 */
	TemplateInfo getTaskDatumDetail(TaskDatumDetailReq req);
	/**
	 * 获取我的资料列表
	 * @author hailongzhao
	 * @date 20151125
	 * @param req
	 * @return
	 */
	 List<TaskDatumModel> getMyTaskDatumList(TaskDatumReq req);
}
