package com.renrentui.renrenentity.req;
/**
 * 任务详情 请求实体
 * 2015年9月29日10:54:11
 * @author ofmyi_000
 *
 */
public class TaskDetailReq {
	private int  userId;
	private int  taskId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
