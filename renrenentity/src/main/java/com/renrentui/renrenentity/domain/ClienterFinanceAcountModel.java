package com.renrentui.renrenentity.domain;

import java.util.Date;

import com.renrentui.renrenentity.ClienterFinanceAcount;

public class ClienterFinanceAcountModel{
	private int withdrawId;
	private int clienterId;
	private Date createtime;
	private double amount;
	
	private double handCharge;
	
	private String payFailedReason;
	
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getHandCharge() {
		return handCharge;
	}

	public void setHandCharge(double handCharge) {
		this.handCharge = handCharge;
	}

	public String getPayFailedReason() {
		return payFailedReason;
	}

	public void setPayFailedReason(String payFailedReason) {
		this.payFailedReason = payFailedReason;
	}

	public int getClienterId() {
		return clienterId;
	}

	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}

	public int getWithdrawId() {
		return withdrawId;
	}

	public void setWithdrawId(int withdrawId) {
		this.withdrawId = withdrawId;
	}
}
