package com.renrentui.renrenentity.req;
/**
* @Des 客户端登陆 实体类
* @Author WangXuDan
* @Date 2015年9月28日14:44:55
* @Return
*/
public class SignInReq {
	private String phoneNo;//手机号
	private String passWord;//密码
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
}
