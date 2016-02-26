package com.renrentui.renrenentity;

import java.util.Date;

public class TaskTag {
	private Long id;
	private String tagName;
	private String tagColorCode;
	private Date createTime;
	private String createName;
	private Date updateTime;
	private String updateName;
	private int tagStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagColorCode() {
		return tagColorCode;
	}
	public void setTagColorCode(String tagColorCode) {
		this.tagColorCode = tagColorCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public int getTagStatus() {
		return tagStatus;
	}
	public void setTagStatus(int tagStatus) {
		this.tagStatus = tagStatus;
	}

}
