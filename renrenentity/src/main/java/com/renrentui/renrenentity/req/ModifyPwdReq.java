package com.renrentui.renrenentity.req;
/**
 * 
 * 修改密码 为了以后BC公用实体 这是用userID  当作 clienterID  和 businessID
 * @author ofmyi_000
 *
 */
public class ModifyPwdReq {

	private int userId;
	private String oldPwd;
	private String newPwd;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
