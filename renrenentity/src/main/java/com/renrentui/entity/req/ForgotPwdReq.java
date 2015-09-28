package com.renrentui.entity.req;
/**
 * 
 * 忘记密码实体类
 * @author ofmyi_000
 * 2015年9月28日10:41:30
 */
public class ForgotPwdReq {

	private String phoneNo;//手机号
	private String passWord;//新密码
	private String verifyCode;//验证码
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
