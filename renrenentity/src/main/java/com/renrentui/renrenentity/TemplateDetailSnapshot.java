package com.renrentui.renrenentity;

public class TemplateDetailSnapshot {
    private Long id;

    private Long templateid;

    private Long controlid;

    private Integer ordernum;

    private String name;

    private String title;

    private String defaultvalue;

    private String controldata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Long templateid) {
        this.templateid = templateid;
    }

    public Long getControlid() {
        return controlid;
    }

    public void setControlid(Long controlid) {
        this.controlid = controlid;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue == null ? null : defaultvalue.trim();
    }

    public String getControldata() {
        return controldata;
    }

    public void setControldata(String controldata) {
        this.controldata = controldata == null ? null : controldata.trim();
    }
}