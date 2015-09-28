package com.renrentui.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ClienterWithdrawForm {
    private Long id;

    private Long clienterId;

    private BigDecimal amount;

    private String withdrawNo;

    private Short withType;

    private String accountInfo;

    private String trueName;

    private Short status;

    private Date createTime;

    private Date auditTime;

    private String auditName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    
}