package com.renrentui.renrenentity.req;

public class BindAliPayReq {
	private String phoneNo;//手机号
	private String aliAccount;//支付宝账号
	private String aliName;//支付宝实名
	private String verifyCode;//验证码
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAliAccount() {
		return aliAccount;
	}
	public void setAliAccount(String aliAccount) {
		this.aliAccount = aliAccount;
	}
	public String getAliName() {
		return aliName;
	}
	public void setAliName(String aliName) {
		this.aliName = aliName;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
