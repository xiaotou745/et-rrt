package com.renrentui.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RenRenTask {
    private Long id;

    private String taskTitle;

    private String taskNotice;

    private String taskGeneralInfo;

    private Long businessId;

    private String pusher;

    private String createName;

    private Date createTime;

    private String modifyName;

    private Date modifyTime;

    private Date beginTime;

    private Date endTime;

    private Double taskCycle;

    private Integer availableCount;

    private BigDecimal amount;

    private Integer state;

    private Integer taskToatlCount;

    private Long tempateId;

    private String link;

    private Short paymentMethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getPusher() {
        return pusher;
    }

    public void setPusher(String pusher) {
        this.pusher = pusher == null ? null : pusher.trim();
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

    

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    
}