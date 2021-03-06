package com.renrentui.renrenentity;


import java.util.Date;

public class BusinessBalanceRecord {
    private Long id;

    private Long businessId;

    private Double amount;

    private Double afterAmount;

    private Short recordType;

    private String optName;

    private Date operateTime;

    private Long orderId;

    private String relationNo;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    

    public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Double getAfterAmount() {
		return afterAmount;
	}

	public void setAfterAmount(Double afterAmount) {
		this.afterAmount = afterAmount;
	}

	public Short getRecordType() {
		return recordType;
	}

	public void setRecordType(Short recordType) {
		this.recordType = recordType;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getRelationNo() {
		return relationNo;
	}

	public void setRelationNo(String relationNo) {
		this.relationNo = relationNo;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}