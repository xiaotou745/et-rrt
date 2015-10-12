package com.renrentui.renrenentity.req;

public class UpdateStatusReq {
	private long reocrdId;
	private int status;
	private int oldStatus;
	private String userName;
	public long getReocrdId() {
		return reocrdId;
	}
	public void setReocrdId(long reocrdId) {
		this.reocrdId = reocrdId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
