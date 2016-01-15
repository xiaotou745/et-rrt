package com.renrentui.renrenentity;


import java.util.Date;

import com.renrentui.renrencore.enums.CBalanceRecordType;

public class ClienterBalanceRecord {
    private Long id;

    private Long clienterId;

    private Double amount;

    private Double afterAmount;

    private Short recordType;
    private String recordTypeName;
    private String optName;

    private Date operateTime;

    private Long orderId;

    private String relationNo;

    private String remark;
    
    private Short status;    
    private String statusName;
    private double withdrawAmount;
    public String getStatusName() {
		return status==Short.valueOf("1")?"交易成功":"交易中";
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

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

    

    public Long getClienterId() {
		return clienterId;
	}

	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
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

	public String getRecordTypeName() {
		return CBalanceRecordType.getEnum(recordType).desc();
	}

	public double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
 
}