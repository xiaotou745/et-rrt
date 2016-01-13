package com.renrentui.renrenentity.domain;

import java.util.Date;
import java.util.List;

import com.renrentui.renrencore.enums.TaskCode;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.enums.TaskType;

public class TaskDatumModel {
	private long taskId;
    private String taskName;
    private double amount;
    private int taskType;
    private String taskTypeName;
    private String taskStatus;
    private String taskStatusName;
    private int auditCycle;
    private long taskDatumId;
    private int auditStatus;
    private Date createDate;
    private Date auditTime;
    private int groupType;
    private List<String> titlesList;
    private long ctId;
    private String refuReason;
	 public long getCtId() {
		return ctId;
	}
	public void setCtId(long ctId) {
		this.ctId = ctId;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
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
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public int getAuditCycle() {
		return auditCycle;
	}
	public void setAuditCycle(int auditCycle) {
		this.auditCycle = auditCycle;
	}
	public long getTaskDatumId() {
		return taskDatumId;
	}
	public void setTaskDatumId(long taskDatumId) {
		this.taskDatumId = taskDatumId;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public List<String> getTitlesList() {
		return titlesList;
	}
	public void setTitlesList(List<String> titlesList) {
		this.titlesList = titlesList;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskTypeName() {
		return TaskType.getEnum(taskType).desc().replace("任务", "");
	}
	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
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
	public String getRefuReason() {
		return refuReason;
	}
	public void setRefuReason(String refuReason) {
		this.refuReason = refuReason;
	}
	
}
