package com.renrentui.renrenentity.domain;

public class TaskDatumDetail {
    private long groupId;
    private int orderNum;
    private long controlTypeId;
    private String controlKey;
    private String controlTitle;
    private String defaultValue;
	private String controlData;
    private String controlValue;
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public long getControlTypeId() {
		return controlTypeId;
	}
	public void setControlTypeId(long controlTypeId) {
		this.controlTypeId = controlTypeId;
	}
	public String getControlKey() {
		return controlKey;
	}
	public void setControlKey(String controlKey) {
		this.controlKey = controlKey;
	}
	public String getControlTitle() {
		return controlTitle;
	}
	public void setControlTitle(String controlTitle) {
		this.controlTitle = controlTitle;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getControlData() {
		return controlData;
	}
	public void setControlData(String controlData) {
		this.controlData = controlData;
	}
	public String getControlValue() {
		return controlValue;
	}
	public void setControlValue(String controlValue) {
		this.controlValue = controlValue;
	}
}
