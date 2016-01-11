package com.renrentui.renrenentity.domain;

public class AlipayCallBackData {
	/// <summary>
    /// 提现单ID(流水号)
    /// </summary>
    private Long withdrawId;
    /// <summary>
    /// 支付宝账号
    /// </summary>
    private String accountNo;
    /// <summary>
    /// 实际打款金额
    /// </summary>
    private double paidAmount;
    /// <summary>
    /// 打款账号名称
    /// </summary>
    private String trueName;
    /// <summary>
    /// 原因
    /// </summary>
    private String reason;
    /// <summary>
    /// 状态 S,F
    /// </summary>
    private String status;
    /// <summary>
    /// 支付宝内部流水号
    /// </summary>
    private String alipayInnerNo;
    public Long getWithdrawId() {
		return withdrawId;
	}
	public void setWithdrawId(Long withdrawId) {
		this.withdrawId = withdrawId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAlipayInnerNo() {
		return alipayInnerNo;
	}
	public void setAlipayInnerNo(String alipayInnerNo) {
		this.alipayInnerNo = alipayInnerNo;
	}
	
}
