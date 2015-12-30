package com.renrentui.renrenentity.resp;

import com.renrentui.renrenentity.Clienter;
/**
* @Des 地推员列表获取返回参数实体类 
* @Author WangXuDan
* @Date 2015年9月29日17:03:01
* @Return
*/
public class ClienterResp extends Clienter {
	private Double balance;
	private Double withdraw;
	private Double hadWithdraw;
	private Double subAmount;
	public Double getSubAmount() {
		return subAmount;
	}
	public void setSubAmount(Double subAmount) {
		this.subAmount = subAmount;
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

}
