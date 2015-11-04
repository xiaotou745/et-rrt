package com.renrentui.renrenentity.domain;

import com.renrentui.renrenentity.Clienter;

public class ClienterDetail extends Clienter {
	private Long clienterId;

    private Double balance;

    private Double withdraw;

    private Double hadWithdraw;
    
    private Double checking;
    
    private Double withdrawing;
    
    private Double totalAmount;

    private String fullHeadImage;
    
	public String getFullHeadImage() {
		return fullHeadImage;
	}

	public void setFullHeadImage(String fullHeadImage) {
		this.fullHeadImage = fullHeadImage;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Double withdraw) {
		this.withdraw = withdraw;
	}

	public Double getHadWithdraw() {
		return hadWithdraw;
	}

	public void setHadWithdraw(Double hadWithdraw) {
		this.hadWithdraw = hadWithdraw;
	}

	public Double getChecking() {
		return checking;
	}

	public void setChecking(Double checking) {
		this.checking = checking;
	}

	public Long getClienterId() {
		return clienterId;
	}

	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}

	public Double getWithdrawing() {
		return withdrawing;
	}

	public void setWithdrawing(Double withdrawing) {
		this.withdrawing = withdrawing;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
