package com.renrentui.renrenentity.resp;

public class SignUpResp {
	private Integer userId;
	private String userName;
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getUserId (){
		return userId;
	}
	
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
}
