package com.renrentui.renrenentity.domain;

import java.io.Serializable;
import java.util.Date;

import com.renrentui.renrencore.enums.OrderStatus;
import com.renrentui.renrencore.enums.PaymentMethodType;
import com.renrentui.renrencore.util.PropertyUtils;

public class TaskModel implements Serializable {
	private long taskId;
	
	private long myReceivedTaskId;
	private String taskGeneralInfo;
	private long orderId;
	private String pusher;
	
	private String taskName;
	
	private double amount;
	
	private int availableCount;
	
	private int status;
	private int auditStatus;
	private String beginTime;
	
	private String endTime;
	
	private int paymentMethod;
	
	private String logo;
	
	private Double taskCycle;
	/*
	 * 领取任务的时间
	 */
	private String receivedTime;
	
	private String auditTime;
	
	private String finishTime;
	
	private Integer waitAuditCount;
	
	public String getAuditTime() {
		return auditTime;
	}
	private int isAgainPickUp;

	public int getIsAgainPickUp() {
		return isAgainPickUp;
	}

	public void setIsAgainPickUp(int isAgainPickUp) {
		this.isAgainPickUp = isAgainPickUp;
	}

	public void setAuditTime(String auditTime) {
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

	public String getStatus() {
		return OrderStatus.getEnum(status).desc();
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	} 
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPaymentMethod() {
		return PaymentMethodType.getEnum(paymentMethod).desc();
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getLogo() {
		if(!this.logo.equals(""))
			return PropertyUtils.getProperty("ImgShowUrl")+this.logo;
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

	public String getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getWaitAuditCount() {
		return waitAuditCount;
	}

	public void setWaitAuditCount(Integer waitAuditCount) {
		this.waitAuditCount = waitAuditCount;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	} 
}
