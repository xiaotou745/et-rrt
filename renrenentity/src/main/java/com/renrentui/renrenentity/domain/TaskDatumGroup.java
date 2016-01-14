package com.renrentui.renrenentity.domain;

import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.enums.TaskType;
import com.renrentui.renrencore.util.PropertyUtils;

public class TaskDatumGroup {
    private String logo;
	private long taskId;
    private String taskName;
    private double amount;
    private int taskType;
	private String taskStatusName;
    private String taskTypeName;
    private String taskStatus;
    private long taskDatumCount;
    private long ctId;
    public String getLogo() {
		if (this.logo != null && !this.logo.equals(""))
			return PropertyUtils.getProperty("ImgShowUrl") + this.logo;
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getTaskType() {
		return taskType;
	}
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
	public String getTaskTypeName() {
		return TaskType.getEnum(taskType).desc().replace("任务", "");
	}
	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskStatusName() {
		if (Integer.parseInt(taskStatus)==TaskStatus.Audited.value()) {
			return "进行中";
		}else {
			return "已过期";
		}
	}
	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
	}
	public long getTaskDatumCount() {
		return taskDatumCount;
	}
	public void setTaskDatumCount(long taskDatumCount) {
		this.taskDatumCount = taskDatumCount;
	}
	public long getCtId() {
		return ctId;
	}
	public void setCtId(long ctId) {
		this.ctId = ctId;
	}

}
