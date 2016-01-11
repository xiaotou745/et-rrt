package com.renrentui.renrenentity.domain;

public class ClienterWithdrawLogModel extends ClienterWithdrawLog {
	private int oldStatus;
	private int dealStatus;
	private String payFailedReason;
	private int isCallBack;
	
	private String callBackRequestId;
	public int getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(int oldStatus) {
		this.oldStatus = oldStatus;
	}

	public int getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(int dealStatus) {
		this.dealStatus = dealStatus;
	}

	public int getIsCallBack() {
		return isCallBack;
	}

	public void setIsCallBack(int isCallBack) {
		this.isCallBack = isCallBack;
	}

	public String getCallBackRequestId() {
		return callBackRequestId;
	}

	public void setCallBackRequestId(String callBackRequestId) {
		this.callBackRequestId = callBackRequestId;
	}

	public String getPayFailedReason() {
		return payFailedReason;
	}

	public void setPayFailedReason(String payFailedReason) {
		this.payFailedReason = payFailedReason;
	} 
}
