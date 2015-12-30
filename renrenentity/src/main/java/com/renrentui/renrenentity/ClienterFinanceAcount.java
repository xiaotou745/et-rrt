package com.renrentui.renrenentity;

import java.util.Date;

public class ClienterFinanceAcount {
    private Integer id;

    private Integer clienterid;

    private String truename;

    private String accountno;

    private Boolean isenable;

    private Short accounttype;

    private Short belongtype;

    private String openbank;

    private String opensubbank;

    private String createby;

    private Date createtime;

    private String updateby;

    private Date updatetime;

    private String idcard;

    private String openprovince;

    private String opencity;

    private String yeepaykey;

    private Short yeepaystatus;

    private Integer openprovincecode;

    private Integer opencitycode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienterid() {
        return clienterid;
    }

    public void setClienterid(Integer clienterid) {
        this.clienterid = clienterid;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno == null ? null : accountno.trim();
    }

    public Boolean getIsenable() {
        return isenable;
    }

    public void setIsenable(Boolean isenable) {
        this.isenable = isenable;
    }

    public Short getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Short accounttype) {
        this.accounttype = accounttype;
    }

    public Short getBelongtype() {
        return belongtype;
    }

    public void setBelongtype(Short belongtype) {
        this.belongtype = belongtype;
    }

    public String getOpenbank() {
        return openbank;
    }

    public void setOpenbank(String openbank) {
        this.openbank = openbank == null ? null : openbank.trim();
    }

    public String getOpensubbank() {
        return opensubbank;
    }

    public void setOpensubbank(String opensubbank) {
        this.opensubbank = opensubbank == null ? null : opensubbank.trim();
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getOpenprovince() {
        return openprovince;
    }

    public void setOpenprovince(String openprovince) {
        this.openprovince = openprovince == null ? null : openprovince.trim();
    }

    public String getOpencity() {
        return opencity;
    }

    public void setOpencity(String opencity) {
        this.opencity = opencity == null ? null : opencity.trim();
    }

    public String getYeepaykey() {
        return yeepaykey;
    }

    public void setYeepaykey(String yeepaykey) {
        this.yeepaykey = yeepaykey == null ? null : yeepaykey.trim();
    }

    public Short getYeepaystatus() {
        return yeepaystatus;
    }

    public void setYeepaystatus(Short yeepaystatus) {
        this.yeepaystatus = yeepaystatus;
    }

    public Integer getOpenprovincecode() {
        return openprovincecode;
    }

    public void setOpenprovincecode(Integer openprovincecode) {
        this.openprovincecode = openprovincecode;
    }

    public Integer getOpencitycode() {
        return opencitycode;
    }

    public void setOpencitycode(Integer opencitycode) {
        this.opencitycode = opencitycode;
    }
}