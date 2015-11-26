package com.renrentui.renrenentity.req;

public class TaskDatumReq {
	private int userId;
	private int taskId;
	private long nextId;
	private int itemsCount;
	private int auditStatus;
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
	public long getNextId() {
		return nextId;
	}
	public void setNextId(long nextId) {
		this.nextId = nextId;
	}
	public int getItemsCount() {
		return itemsCount;
	}
	public void setItemsCount(int itemsCount) {
		this.itemsCount = itemsCount;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
}
