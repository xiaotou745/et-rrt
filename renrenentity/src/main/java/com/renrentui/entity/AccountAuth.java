package com.renrentui.entity;

import java.util.Date;

public class AccountAuth {
    private Integer id;

    private Integer accoutid;

    private Integer menuid;

    private Date createtime;

    private String optname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccoutid() {
        return accoutid;
    }

    public void setAccoutid(Integer accoutid) {
        this.accoutid = accoutid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname == null ? null : optname.trim();
    }
}