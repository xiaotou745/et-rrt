package com.renrentui.renrenentity;

import java.util.Date;

public class ClienterWx {
	private int id;
	private String openId;
	private String fromUserName;
	private String wxId;
	private int followStatus;
	private Date followTime;
	private int clienterId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getWxId() {
		return wxId;
	}
	public void setWxId(String wxId) {
		this.wxId = wxId;
	}
	public int getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(int followStatus) {
		this.followStatus = followStatus;
	}
	public Date getFollowTime() {
		return followTime;
	}
	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}
	public int getClienterId() {
		return clienterId;
	}
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}
	
	
	
	
}
