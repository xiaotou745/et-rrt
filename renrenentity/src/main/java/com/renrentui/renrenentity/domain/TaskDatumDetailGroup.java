package com.renrentui.renrenentity.domain;

import java.util.List;

public class TaskDatumDetailGroup {
	private long taskDatumId;
    private long taskId;
    private long groupId;
    private int groupType;
	private String title;
	private List<TaskDatumDetail> controlList;
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
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<TaskDatumDetail> getControlList() {
		return controlList;
	}
	public void setControlList(List<TaskDatumDetail> controlList) {
		this.controlList = controlList;
	}
}
