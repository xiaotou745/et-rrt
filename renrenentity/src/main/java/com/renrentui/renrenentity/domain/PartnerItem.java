package com.renrentui.renrenentity.domain;

import java.util.Date;

import com.renrentui.renrencore.util.PropertyUtils;

public class PartnerItem {
	private Long id;
	private String clienterName;
	private String phoneNo;
	private String headImage;
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getHeadImage() {
		if (headImage!=null&&!headImage.isEmpty()) {
			int index=headImage.lastIndexOf(".");
			if (index<=0) {
				return "";
			}
			String fileName=headImage.substring(0, index);
			String extension=headImage.substring(index);
			return PropertyUtils.getProperty("ImgShowUrl")+ fileName+"_95_95"+extension;
		}
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
