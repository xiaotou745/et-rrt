package com.renrentui.entity;

import java.util.Date;

public class RoleInfo {
    private Integer id;

    private String rolename;

    private Date createtime;

    private String remark;

    private String optname;

    private Boolean belock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname == null ? null : optname.trim();
    }

    public Boolean getBelock() {
        return belock;
    }

    public void setBelock(Boolean belock) {
        this.belock = belock;
    }
}