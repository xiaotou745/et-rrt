package com.renrentui.renrenentity.domain;

public class AlipayClienterWithdrawModel {
	/// <summary>
	/// 自增ID(PK)
	/// </summary>
	private long id;
	/// <summary>
	/// 提现单号
	/// </summary>
	private String withdrawNo;
	
	/// <summary>
	/// 提现金额
	/// </summary>
	private double amount;

	/// <summary>
	/// 骑士收款户名
	/// </summary>
	private String trueName;

	/// <summary>
	/// 骑士收款卡号(DES加密)
	/// </summary>
	private String accountInfo;


	/// <summary>
	/// 骑士付给我们的手续费
	/// </summary>

	private double handCharge;

	private double actualHandCharge;
	/// <summary>
	/// 实付金额
	/// </summary>
	private double actualAmount;

	private String alipayBatchNo ;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	 
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
 

	public double getHandCharge() {
		return handCharge;
	}

	public void setHandCharge(double handCharge) {
		this.handCharge = handCharge;
	} 
	 
	public String getAlipayBatchNo() {
		return alipayBatchNo;
	}

	public void setAlipayBatchNo(String alipayBatchNo) {
		this.alipayBatchNo = alipayBatchNo;
	}

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public double getActualHandCharge() {
		return actualHandCharge;
	}

	public void setActualHandCharge(double actualHandCharge) {
		this.actualHandCharge = actualHandCharge;
	}

	public String getWithdrawNo() {
		return withdrawNo;
	}

	public void setWithdrawNo(String withdrawNo) {
		this.withdrawNo = withdrawNo;
	} 
}
