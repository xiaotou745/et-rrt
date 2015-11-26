package com.renrentui.renrenentity.req;

public class TaskMsgUpdateReq {
	private int userId;
	private int msgId;
	private int opType;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public int getOpType() {
		return opType;
	}
	public void setOpType(int opType) {
		this.opType = opType;
	}
}
