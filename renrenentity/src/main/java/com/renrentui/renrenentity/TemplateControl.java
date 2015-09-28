package com.renrentui.renrenentity;

public class TemplateControl {
    private Long id;

    private String controlType;

    private String contolName;

    private Short status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getContolName() {
		return contolName;
	}

	public void setContolName(String contolName) {
		this.contolName = contolName;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
}