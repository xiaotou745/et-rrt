package com.renrentui.renrenentity.req;
/***
 * 订单审核实体类
 * @author ofmyi_000
 *
 */
public class OrderAuditReq {
	private Long orderId;
	private int  auditStatus;
	private String auditName;
	private Long userId;
	private double amount;
	private String orderNo;
	private String refuReason;
	public String getRefuReason() {
		return refuReason==null?"":refuReason;
	}
	public void setRefuReason(String refuReason) {
		this.refuReason = refuReason;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
}
