package com.renrentui.entity;

import java.util.Date;

public class Template {
    private Long id;

    private Long businessid;

    private String templatename;

    private Date createtime;

    private String lastoptname;

    private Date lastopttime;

    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Long businessid) {
        this.businessid = businessid;
    }

    public String getTemplatename() {
        return templatename;
    }

    public void setTemplatename(String templatename) {
        this.templatename = templatename == null ? null : templatename.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLastoptname() {
        return lastoptname;
    }

    public void setLastoptname(String lastoptname) {
        this.lastoptname = lastoptname == null ? null : lastoptname.trim();
    }

    public Date getLastopttime() {
        return lastopttime;
    }

    public void setLastopttime(Date lastopttime) {
        this.lastopttime = lastopttime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}