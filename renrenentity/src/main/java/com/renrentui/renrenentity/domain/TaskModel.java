package com.renrentui.renrenentity.domain;

import java.io.Serializable;
import java.util.Date;

public class TaskModel implements Serializable {
	private long taskId;
	
	private long myReceivedTaskId;
	private String taskGeneralInfo;
	
	private String pusher;
	
	private String taskName;
	
	private double amount;
	
	private int availableCount;
	
	private int status;
	
	private Date beginTime;
	
	private Date endTime;
	
	private int paymentMethod;
	
	private String logo;
	
	private Double taskCycle;
	/*
	 * 领取任务的时间
	 */
	private Date receivedTime;
	
	private Date auditTime;
	
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	
	public long getMyReceivedTaskId() {
		return myReceivedTaskId;
	}

	public void setMyReceivedTaskId(long myReceivedTaskId) {
		this.myReceivedTaskId = myReceivedTaskId;
	}

	public String getTaskGeneralInfo() {
		return taskGeneralInfo;
	}

	public void setTaskGeneralInfo(String taskGeneralInfo) {
		this.taskGeneralInfo = taskGeneralInfo;
	}

	public String getPusher() {
		return pusher;
	}

	public void setPusher(String pusher) {
		this.pusher = pusher;
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

	public int getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	} 
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Double getTaskCycle() {
		return taskCycle;
	}

	public void setTaskCycle(Double taskCycle) {
		this.taskCycle = taskCycle;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}
	
	
}
