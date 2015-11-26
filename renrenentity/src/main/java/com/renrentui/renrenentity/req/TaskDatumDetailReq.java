package com.renrentui.renrenentity.req;

public class TaskDatumDetailReq {
	private long userId;
	private long taskId;
	private long taskDatumId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getTaskDatumId() {
		return taskDatumId;
	}
	public void setTaskDatumId(long taskDatumId) {
		this.taskDatumId = taskDatumId;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
}
