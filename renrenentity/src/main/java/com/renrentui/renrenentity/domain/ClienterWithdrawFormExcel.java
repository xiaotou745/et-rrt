package com.renrentui.renrenentity.domain;

public class ClienterWithdrawFormExcel {
	private String clienterName;
	
	private String accountInfo;
	private String acountName;
    private String trueName; 
    private double amount;
    private double handCharge;
    private double actualAmount;
    private double actualHandCharge;
    private String createTime;
    private String statusString;
    private String phoneNo;
    private String withdrawNo;
	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getAcountName() {
		return acountName;
	}

	public void setAcountName(String acountName) {
		this.acountName = acountName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	} 
	
	public String getClienterName() {
		return clienterName;
	}

	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public double getHandCharge() {
		return handCharge;
	}

	public void setHandCharge(double handCharge) {
		this.handCharge = handCharge;
	}

	public String getWithdrawNo() {
		return withdrawNo;
	}

	public void setWithdrawNo(String withdrawNo) {
		this.withdrawNo = withdrawNo;
	}

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public double getActualHandCharge() {
		return actualHandCharge;
	}

	public void setActualHandCharge(double actualHandCharge) {
		this.actualHandCharge = actualHandCharge;
	}
}
