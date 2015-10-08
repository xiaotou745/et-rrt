package com.renrentui.renrenentity.req;

public class ModifyClienterStatusReq {
	private int userId;
	private int status;
	private int oldStatus;
	private String lastOptName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(int oldStatus) {
		this.oldStatus = oldStatus;
	}
	public String getLastOptName() {
		return lastOptName;
	}
	public void setLastOptName(String lastOptName) {
		this.lastOptName = lastOptName;
	}

}
