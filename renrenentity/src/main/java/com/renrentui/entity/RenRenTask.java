package com.renrentui.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RenRenTask {
    private Long id;

    private String tasktitle;

    private String tasknotice;

    private String taskgeneralinfo;

    private Long businessid;

    private String pusher;

    private String createname;

    private Date createtime;

    private String modifyname;

    private Date modifytime;

    private Date begintime;

    private Date endtiime;

    private Double taskcycle;

    private Integer availablecount;

    private BigDecimal amount;

    private Integer state;

    private Integer tasktoatlcount;

    private Long tempateid;

    private String link;

    private Short paymentmethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle == null ? null : tasktitle.trim();
    }

    public String getTasknotice() {
        return tasknotice;
    }

    public void setTasknotice(String tasknotice) {
        this.tasknotice = tasknotice == null ? null : tasknotice.trim();
    }

    public String getTaskgeneralinfo() {
        return taskgeneralinfo;
    }

    public void setTaskgeneralinfo(String taskgeneralinfo) {
        this.taskgeneralinfo = taskgeneralinfo == null ? null : taskgeneralinfo.trim();
    }

    public Long getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Long businessid) {
        this.businessid = businessid;
    }

    public String getPusher() {
        return pusher;
    }

    public void setPusher(String pusher) {
        this.pusher = pusher == null ? null : pusher.trim();
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getModifyname() {
        return modifyname;
    }

    public void setModifyname(String modifyname) {
        this.modifyname = modifyname == null ? null : modifyname.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtiime() {
        return endtiime;
    }

    public void setEndtiime(Date endtiime) {
        this.endtiime = endtiime;
    }

    public Double getTaskcycle() {
        return taskcycle;
    }

    public void setTaskcycle(Double taskcycle) {
        this.taskcycle = taskcycle;
    }

    public Integer getAvailablecount() {
        return availablecount;
    }

    public void setAvailablecount(Integer availablecount) {
        this.availablecount = availablecount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTasktoatlcount() {
        return tasktoatlcount;
    }

    public void setTasktoatlcount(Integer tasktoatlcount) {
        this.tasktoatlcount = tasktoatlcount;
    }

    public Long getTempateid() {
        return tempateid;
    }

    public void setTempateid(Long tempateid) {
        this.tempateid = tempateid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Short getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(Short paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}