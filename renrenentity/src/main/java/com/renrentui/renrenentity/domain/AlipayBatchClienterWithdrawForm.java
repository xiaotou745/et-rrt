package com.renrentui.renrenentity.domain;

public class AlipayBatchClienterWithdrawForm {
	private int id;
	private String withdrawNo;
	private String accountInfo;
	private String trueName;
	private Double amount;
	private int status;
	private String lastOptUser;
	private String payFailedReason;

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	} 
	/**
	 * 支付宝用户名
	 * 
	 * @return
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * 支付宝用户名
	 * 
	 * @param trueName
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * 金额
	 * 
	 * @return
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 金额
	 * 
	 * @param amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 提现单 状态
	 * 
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 提现单 状态
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 操作人
	 * 
	 * @return
	 */
	public String getLastOptUser() {
		return lastOptUser;
	}

	/**
	 * 操作人
	 * 
	 * @param lastOptUser
	 */
	public void setLastOptUser(String lastOptUser) {
		this.lastOptUser = lastOptUser;
	}

	/**
	 * 打款失败原因
	 * 
	 * @return
	 */
	public String getPayFailedReason() {
		return payFailedReason;
	}

	/**
	 * 打款失败原因
	 * 
	 * @param payFailedReason
	 */
	public void setPayFailedReason(String payFailedReason) {
		this.payFailedReason = payFailedReason;
	}

	public String getWithdrawNo() {
		return withdrawNo;
	}

	public void setWithdrawNo(String withdrawNo) {
		this.withdrawNo = withdrawNo;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}
}
