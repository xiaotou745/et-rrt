package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class PagedPartnerReq  extends PagedRequestBase {
    private String taskTitle;
	private String clienterName;
    private String clienterPhoneNo;
	private String receiveTimeBegin;
    private String receiveTimeEnd;
    private long cityCode;
    private String cityName;
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getClienterName() {
		return clienterName;
	}
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}
	public String getClienterPhoneNo() {
		return clienterPhoneNo;
	}
	public void setClienterPhoneNo(String clienterPhoneNo) {
		this.clienterPhoneNo = clienterPhoneNo;
	}
	public String getReceiveTimeBegin() {
		return receiveTimeBegin;
	}
	public void setReceiveTimeBegin(String receiveTimeBegin) {
		this.receiveTimeBegin = receiveTimeBegin;
	}
	public String getReceiveTimeEnd() {
		return receiveTimeEnd;
	}
	public void setReceiveTimeEnd(String receiveTimeEnd) {
		this.receiveTimeEnd = receiveTimeEnd;
	}
	public long getCityCode() {
		return cityCode;
	}
	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
