package com.renrentui.entity;

import java.util.Date;

public class ClienterLog {
    private Long id;

    private Long clienterid;

    private Date createtime;

    private String optname;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienterid() {
        return clienterid;
    }

    public void setClienterid(Long clienterid) {
        this.clienterid = clienterid;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}