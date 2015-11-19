package com.renrentui.renrenentity.domain;
/**
 * 模板组控件信息
 * @author ofmyi_000
 *
 */
public class TemCorModel {
	
	private Integer groupType;
	private Long groupId;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	private String groupTitle;
	private Integer orderNum;
	private String name;
	private String controlTitle;
	private String defaultValue;
	private String controlData;
	private String controlType;
	public Integer getGroupType() {
		return groupType;
	}
	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	public String getGroupTitle() {
		return groupTitle;
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
}
