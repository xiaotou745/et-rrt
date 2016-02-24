package com.renrentui.renrenentity.domain;

import java.util.Date;

public class ClienterTask {
	private Long id;
	private long cityCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 任务ID
	 */
	private Long taskId;
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	/**
	 * 地推员ID
	 */
	private Long clienterId;
	public Long getClienterId() {
		return clienterId;
	}
	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}

	/**
	 * 商家ID
	 */
	private Long businessId;
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * 任务单价
	 */
	private Double amount;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 状态 默认1 领取中 2过期(不在列表显示) 
	 */
	private Integer status;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 完成次数
	 */
	private Integer completeNum;
	public Integer getCompleteNum() {
		return completeNum;
	}
	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}

	/**
	 * 任务类型(参见任务表)
	 */
	private Integer taskType;
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	/**
	 * 创建时间
	 */
	private Date createDate;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getCityCode() {
		return cityCode;
	}
	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}

}
