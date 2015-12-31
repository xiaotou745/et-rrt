package com.renrentui.renrenentity.domain;

import com.renrentui.renrenentity.Clienter;

public class ClienterDetail extends Clienter {

    private Double balance;

    private Double withdraw;

    private Double hadWithdraw;
    
    private Double checking;
    
    private Double withdrawing;
    
    private Double totalAmount;

    private String fullHeadImage;
    
    private String accountNo;
    private String trueName;
    private int accountType;
    
    
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

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
