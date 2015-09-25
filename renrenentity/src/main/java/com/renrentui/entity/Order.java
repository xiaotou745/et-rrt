package com.renrentui.entity;

import java.util.Date;

public class Order {
    private Long id;

    private Long clienterid;

    private Long taskid;

    private Short orderstatus;

    private Short auditstatus;

    private Date createtime;

    private Date finishtime;

    private Date audittime;

    private Date auditname;

    private Date deallinetime;

    private Date canceltime;

    private String cancelname;

    private String cancelremark;

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

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Short getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Short orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Short getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(Short auditstatus) {
        this.auditstatus = auditstatus;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public Date getAuditname() {
        return auditname;
    }

    public void setAuditname(Date auditname) {
        this.auditname = auditname;
    }

    public Date getDeallinetime() {
        return deallinetime;
    }

    public void setDeallinetime(Date deallinetime) {
        this.deallinetime = deallinetime;
    }

    public Date getCanceltime() {
        return canceltime;
    }

    public void setCanceltime(Date canceltime) {
        this.canceltime = canceltime;
    }

    public String getCancelname() {
        return cancelname;
    }

    public void setCancelname(String cancelname) {
        this.cancelname = cancelname == null ? null : cancelname.trim();
    }

    public String getCancelremark() {
        return cancelremark;
    }

    public void setCancelremark(String cancelremark) {
        this.cancelremark = cancelremark == null ? null : cancelremark.trim();
    }
}