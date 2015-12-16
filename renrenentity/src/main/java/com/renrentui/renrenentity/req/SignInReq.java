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

	private String sSID;



	private String operSystem;



	private String operSystemModel;



	private String phoneType;


	private String appVersion;

	public String getsSID() {
		return sSID;
	}
	public void setsSID(String sSID) {
		this.sSID = sSID;
	}
	public String getOperSystem() {
		return operSystem;
	}
	public void setOperSystem(String operSystem) {
		this.operSystem = operSystem;
	}
	public String getOperSystemModel() {
		return operSystemModel;
	}
	public void setOperSystemModel(String operSystemModel) {
		this.operSystemModel = operSystemModel;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
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
}
