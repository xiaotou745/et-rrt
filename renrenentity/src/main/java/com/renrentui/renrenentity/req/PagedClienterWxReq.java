package com.renrentui.renrenentity.req;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class PagedClienterWxReq extends PagedRequestBase {
	private String wxName;
	private String wxId;
	
	private String wxNo;
	
	private int followStatus;
	
	private String beiginAttentionDate;
	
	private String endAttentionDate;
	
	private String beiginUnAttentionDate;
	
	private String endUnAttentionDate;
	
	private String clienterPhoneNo;
	
	private int activityid;
	private int recordType;
	public String getWxName() {
		return wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getWxNo() {
		return wxNo;
	}

	public void setWxNo(String wxNo) {
		this.wxNo = wxNo;
	}

	public int getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(int followStatus) {
		this.followStatus = followStatus;
	}

	public String getBeiginAttentionDate() {
		return beiginAttentionDate;
	}

	public void setBeiginAttentionDate(String beiginAttentionDate) {
		this.beiginAttentionDate = beiginAttentionDate;
	}

	public String getEndAttentionDate() {
		return endAttentionDate;
	}

	public void setEndAttentionDate(String endAttentionDate) {
		this.endAttentionDate = endAttentionDate;
	}

	public String getBeiginUnAttentionDate() {
		return beiginUnAttentionDate;
	}

	public void setBeiginUnAttentionDate(String beiginUnAttentionDate) {
		this.beiginUnAttentionDate = beiginUnAttentionDate;
	}

	public String getEndUnAttentionDate() {
		return endUnAttentionDate;
	}

	public void setEndUnAttentionDate(String endUnAttentionDate) {
		this.endUnAttentionDate = endUnAttentionDate;
	}

	public String getClienterPhoneNo() {
		return clienterPhoneNo;
	}

	public void setClienterPhoneNo(String clienterPhoneNo) {
		this.clienterPhoneNo = clienterPhoneNo;
	}

	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	} 
}
