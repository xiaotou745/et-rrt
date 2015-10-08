package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.enums.CancelTaskCode;import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrencore.enums.SubmitTaskCode;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.domain.OrderRetrunModel;import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.TaskCityRelation;import java.util.List;import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
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
	
	
	public OrderRetrunModel getTask(TaskDetailReq req);
	
	/**
	 * 取消任务
	 * @param req
	 * @return
	 */
	public CancelTaskCode cancelTask(CancelTaskReq req);
	
	public SubmitTaskCode submitTask(SubmitTaskReq req);
	public int insert(RenRenTask record,List<Integer> regionCodes);
}
