package com.renrentui.renrenentity.req;

public class SignUpReq {
	private Integer id;
	private String phoneNo;//手机号
	private String passWord;//密码
	private String verifyCode;//验证码
	private String name;  //用户名
	
	public Integer getId() {
        return id;
    } 
    public void setId(Integer id) {
        this.id = id;
    }

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}
