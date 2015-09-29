package com.renrentui.renrenentity.req;

public class CSendCodeReq {
	
	private String phoneNo;//账号
	private int sType;//类型 1注册 2修改密码 3忘记密码
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getsType() {
		return sType;
	}
	public void setsType(int sType) {
		this.sType = sType;
	}
}
