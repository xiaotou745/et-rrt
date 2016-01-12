package com.renrentui.renrenentity;


import java.text.DecimalFormat;
import java.util.Date;

public class ClienterWithdrawForm {
    private Long id;

    private Long clienterId;
    private String auditFailedReason;
    private Double amount;

    private String withdrawNo;

    private Short withType;

    private String accountInfo;

    private String trueName;

    private Short status;

    private Date createTime;

    private Date auditTime;

    private String auditName;
    //实际支付给骑士的金额：骑士体现金额-扣除骑士付给我们的手续费金额
    private double actualAmount;
    //骑士付给我们的手续费金额
    private double handCharge;
    //我们付给支付宝的手续费
    private double actualHandCharge;
    public Long getId() {
        return id;
    }

    public void setId(Long withwardId) {
        this.id = withwardId;
    }

    

    public Double getAmount() {
        return amount;
    }
    public String getAmountString()
    {
      return new DecimalFormat("0.00").format(amount);
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    

    public Short getStatus() {
        return status;
    }

    public Long getClienterId() {
		return clienterId;
	}

	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}

	public String getWithdrawNo() {
		return withdrawNo;
	}

	public void setWithdrawNo(String withdrawNo) {
		this.withdrawNo = withdrawNo;
	}

	public Short getWithType() {
		return withType;
	}

	public void setWithType(Short withType) {
		this.withType = withType;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public void setStatus(Short status) {
        this.status = status;
    }

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public double getHandCharge() {
		return handCharge;
	}

	public void setHandCharge(double handCharge) {
		this.handCharge = handCharge;
	}

	public double getActualHandCharge() {
		return actualHandCharge;
	}

	public void setActualHandCharge(double actualHandCharge) {
		this.actualHandCharge = actualHandCharge;
	}

	public String getAuditFailedReason() {
		return auditFailedReason;
	}

	public void setAuditFailedReason(String auditFailedReason) {
		this.auditFailedReason = auditFailedReason;
	}

    
}