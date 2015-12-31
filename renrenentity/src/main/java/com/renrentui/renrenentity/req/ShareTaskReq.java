package com.renrentui.renrenentity.req;

public class ShareTaskReq {
private String downUrl;
private long taskId;
private long clienterId;
public String getDownUrl() {
	return downUrl;
}
public void setDownUrl(String downUrl) {
	this.downUrl = downUrl;
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

}
