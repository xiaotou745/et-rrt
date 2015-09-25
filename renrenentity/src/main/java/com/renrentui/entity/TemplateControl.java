package com.renrentui.entity;

public class TemplateControl {
    private Long id;

    private String controltype;

    private String contolname;

    private Short status;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getControltype() {
        return controltype;
    }

    public void setControltype(String controltype) {
        this.controltype = controltype == null ? null : controltype.trim();
    }

    public String getContolname() {
        return contolname;
    }

    public void setContolname(String contolname) {
        this.contolname = contolname == null ? null : contolname.trim();
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
}