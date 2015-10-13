package com.renrentui.renrenentity.domain;
/**
 * 任务详情==模板 ==模板控件实体类
 * @author ofmyi_000
 *
 */
public class ControlInfo {

	private String hadValue;
	public String getHadValue() {
		return hadValue;
	}
	public void setHadValue(String hadValue) {
		this.hadValue = hadValue;
	}
	private String controlType;
	private String title;
	private String name;
	private String defaultValue;
	private String controlData;
	private int OrderNum;
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getOrderNum() {
		return OrderNum;
	}
	public void setOrderNum(int orderNum) {
		OrderNum = orderNum;
	}
}
