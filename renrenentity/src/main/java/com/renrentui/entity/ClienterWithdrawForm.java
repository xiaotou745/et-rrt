package com.renrentui.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ClienterWithdrawForm {
    private Long id;

    private Long clienterid;

    private BigDecimal amount;

    private String withdrawno;

    private Short withtype;

    private String accountinfo;

    private String truename;

    private Short status;

    private Date createtime;

    private Date audittime;

    private String auditname;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getWithdrawno() {
        return withdrawno;
    }

    public void setWithdrawno(String withdrawno) {
        this.withdrawno = withdrawno == null ? null : withdrawno.trim();
    }

    public Short getWithtype() {
        return withtype;
    }

    public void setWithtype(Short withtype) {
        this.withtype = withtype;
    }

    public String getAccountinfo() {
        return accountinfo;
    }

    public void setAccountinfo(String accountinfo) {
        this.accountinfo = accountinfo == null ? null : accountinfo.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname == null ? null : auditname.trim();
    }
}