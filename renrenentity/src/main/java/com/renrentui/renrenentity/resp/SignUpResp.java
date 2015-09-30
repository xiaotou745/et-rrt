package com.renrentui.renrenentity.resp;

public class SignUpResp {
	private long userId;
	private String userName;
	public void setUserId(long userId){
		this.userId = userId;
	}
	public long getUserId (){
		return userId;
	}
	
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
}
