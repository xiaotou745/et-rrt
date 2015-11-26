package com.renrentui.renrenentity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskMsg {
	private long id;
	private String title;
	private String msg;
	private Date createDate;
	private boolean hasRead;
	private long taskId;
	
	@JsonIgnore
	private String createName;
	@JsonIgnore
	private Date readDate;
	@JsonIgnore
	private int isDel;
	@JsonIgnore
	private int msgType;
	@JsonIgnore
	private long clienterId;
	@JsonIgnore
	private long taskDatumId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isHasRead() {
		return hasRead;
	}

	public void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}


	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public long getClienterId() {
		return clienterId;
	}

	public void setClienterId(long clienterId) {
		this.clienterId = clienterId;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public long getTaskDatumId() {
		return taskDatumId;
	}

	public void setTaskDatumId(long taskDatumId) {
		this.taskDatumId = taskDatumId;
	}
	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
