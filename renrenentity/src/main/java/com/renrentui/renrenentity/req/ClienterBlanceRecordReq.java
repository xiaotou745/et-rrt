package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class ClienterBlanceRecordReq extends PagedRequestBase{

	private int recordType;
	private int orderId;
	private String beginDate;
	private String endDate;
	private Long clienterId;

	public int getRecordType() {
		return recordType;
	}
	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getClienterId() {
		return clienterId;
	}
	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}
}
