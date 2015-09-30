package com.renrentui.renrenentity;

import java.util.Date;

public class Order {
    private Long id;

    private Long clienterId;

    private Long taskId;

    private Short orderStatus;

    private Short auditStatus;

    private Date createTime;

    private Date finishTime;

    private Date auditTime;

    private Date auditName;

    private Date deadlineTime;

    private Date cancelTime;

    private String cancelName;

    private String cancelRemark;
    private String orderNo;
    public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getId() {
        return id;
    }

    public Long getClienterId() {
		return clienterId;
	}

	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Short getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Short auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getAuditName() {
		return auditName;
	}

	public void setAuditName(Date auditName) {
		this.auditName = auditName;
	}

	public Date getDeadlineTime() {
		return deadlineTime;
	}

	public void setDeadlineTime(Date deadlineTime) {
		this.deadlineTime = deadlineTime;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getCancelName() {
		return cancelName;
	}

	public void setCancelName(String cancelName) {
		this.cancelName = cancelName;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public void setId(Long id) {
        this.id = id;
    }

    
}