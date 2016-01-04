package com.renrentui.renrenentity.domain;

public class ClienterRelationLevelModel {
public Long getClienterId() {
		return clienterId;
	}
	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}
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
	public Integer getCompleteCount() {
		return completeCount;
	}
	public void setCompleteCount(Integer completeCount) {
		this.completeCount = completeCount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getTotalSubAmount() {
		return totalSubAmount;
	}
	public void setTotalSubAmount(Double totalSubAmount) {
		this.totalSubAmount = totalSubAmount;
	}
private Long clienterId;
private String clienterName;
private String phoneNo;
private Integer completeCount;
private Double totalAmount;
private Double totalSubAmount;
}
