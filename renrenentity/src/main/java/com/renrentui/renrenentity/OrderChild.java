package com.renrentui.renrenentity;

public class OrderChild {
    private Long id;
    private Long orderId;
    private String controlName;
    private String controlValue;
    private Long templateSnapshotId;
	public Long getTemplateSnapshotId() {
		return templateSnapshotId;
	}
	public void setTemplateSnapshotId(Long templateSnapshotId) {
		this.templateSnapshotId = templateSnapshotId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
}
