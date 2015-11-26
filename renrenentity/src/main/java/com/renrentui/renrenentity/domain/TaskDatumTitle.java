package com.renrentui.renrenentity.domain;

public class TaskDatumTitle {
	private long taskDatumId;
	private int groupType;
  	private int orderNum;
	private String controlName;
  	private String controlValue;
    public long getTaskDatumId() {
		return taskDatumId;
	}
	public void setTaskDatumId(long taskDatumId) {
		this.taskDatumId = taskDatumId;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	public String getControlName() {
		return controlName;
	}
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}
	public String getControlValue() {
		return controlValue;
	}
	public void setControlValue(String controlValue) {
		this.controlValue = controlValue;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

}
