package com.renrentui.renrenentity.domain;

import java.io.Serializable;
import java.util.Date;

import com.renrentui.renrencore.enums.OrderStatus;
import com.renrentui.renrencore.enums.PaymentMethodType;
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
		return TaskType.getEnum(taskType).desc();
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}
}
