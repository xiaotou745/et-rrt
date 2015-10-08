package com.renrentui.renrenentity.req;
/**
 * 
 * @Des app提现请求参数
 * @author  胡灵波
 * @Date2015年9月28日 11:35:11
 * @Return
 */
public class ClienterBalanceReq {

	private long userId;//用户Id
	private double amount;//提现金额	
	private String accountInfo;
	private String trueName;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
		
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	

}
