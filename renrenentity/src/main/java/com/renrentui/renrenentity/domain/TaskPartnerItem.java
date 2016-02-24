package com.renrentui.renrenentity.domain;

import java.util.Date;

public class TaskPartnerItem {
	private String clienterName;
	private String phoneNo;
	private long clienterId;
	private long cityCode;
	private String cityName;
	private Date receiveDate;
	private long completeNum;
	private long taskID;
	private String taskTitle;
	private int taskStatus;
	private long waitAuditNum;
	private long auditPassNum;
	private long auditRefuseNum;
	private int taskType;
	public String getClienterName() {
		return clienterName;
	}
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public long getCityCode() {
		return cityCode;
	}
	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public long getCompleteNum() {
		return completeNum;
	}
	public void setCompleteNum(long completeNum) {
		this.completeNum = completeNum;
	}
	public long getTaskID() {
		return taskID;
	}
	public void setTaskID(long taskID) {
		this.taskID = taskID;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public int getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	public long getWaitAuditNum() {
		return waitAuditNum;
	}
	public void setWaitAuditNum(long waitAuditNum) {
		this.waitAuditNum = waitAuditNum;
	}
	public long getAuditPassNum() {
		return auditPassNum;
	}
	public void setAuditPassNum(long auditPassNum) {
		this.auditPassNum = auditPassNum;
	}
	public long getAuditRefuseNum() {
		return auditRefuseNum;
	}
	public void setAuditRefuseNum(long auditRefuseNum) {
		this.auditRefuseNum = auditRefuseNum;
	}
	public long getClienterId() {
		return clienterId;
	}
	public void setClienterId(long clienterId) {
		this.clienterId = clienterId;
	}
	public int getTaskType() {
		return taskType;
	}
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}