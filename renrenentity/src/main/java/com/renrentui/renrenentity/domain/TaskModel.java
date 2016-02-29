package com.renrentui.renrenentity.domain;

import java.io.Serializable;
import com.renrentui.renrencore.enums.TaskType;
import com.renrentui.renrencore.util.PropertyUtils;

public class TaskModel implements Serializable {
	private long taskId;
	private String taskGeneralInfo;
	private String taskName;
	private double amount;
	private int taskType;
	private String taskTypeName;
	private String logo;
	private int status;
	
	private int auditCycle;
	private int estimatedTime;
	private int partnerNum;
	private String tagName;
	private String tagColorCode;
	public int getAuditCycle() {
		return auditCycle;
	}

	public void setAuditCycle(int auditCycle) {
		this.auditCycle = auditCycle;
	}

	public int getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public int getPartnerNum() {
		return partnerNum;
	}

	public void setPartnerNum(int partnerNum) {
		this.partnerNum = partnerNum;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskGeneralInfo() {
		return taskGeneralInfo;
	}

	public void setTaskGeneralInfo(String taskGeneralInfo) {
		this.taskGeneralInfo = taskGeneralInfo;
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


	public String getLogo() {
		if (this.logo != null && !this.logo.equals(""))
			return PropertyUtils.getProperty("ImgShowUrl") + this.logo;
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public String getTaskTypeName() {
		return TaskType.getEnum(taskType).desc().replace("任务","");
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagColorCode() {
		return tagColorCode;
	}

	public void setTagColorCode(String tagColorCode) {
		this.tagColorCode = tagColorCode;
	}
}
