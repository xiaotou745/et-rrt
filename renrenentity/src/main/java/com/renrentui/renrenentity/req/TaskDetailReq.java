package com.renrentui.renrenentity.req;
/**
 * 任务详情 请求实体
 * 2015年9月29日10:54:11
 * @author ofmyi_000
 *
 */
public class TaskDetailReq {
	private Long  userId;
	private Long  taskId;
	private Long orderId;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
}
