package com.renrentui.renrenentity.domain;

import com.renrentui.renrencore.util.PropertyUtils;

public class PartnerDetail {
    private Long clienterId;
	private String clienterName;
    private String headImage;
    private String phoneNo;
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
	public String getHeadImage() {
		if (headImage!=null&&!headImage.isEmpty()) {
			return PropertyUtils.getProperty("ImgShowUrl")+ headImage;
		}
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
