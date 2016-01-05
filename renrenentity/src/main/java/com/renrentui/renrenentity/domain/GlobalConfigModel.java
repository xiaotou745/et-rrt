package com.renrentui.renrenentity.domain;


public class GlobalConfigModel {
	private String id;
	private String keyName;
	private String value;
	private String regx;
	private String remark;
	private Integer groupId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String valueString) {
		this.value = valueString;
	}

	public String getRegx() {
		return regx;
	}

	public void setRegx(String regx) {
		this.regx = regx;
	}

}
