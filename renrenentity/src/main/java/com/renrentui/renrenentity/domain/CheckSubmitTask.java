package com.renrentui.renrenentity.domain;
/**
 * 验证任务是否可以提交
 * @author ofmyi_000
 *
 */
public class CheckSubmitTask {
private int submitCan;
private int isCancel;
private int taskClosed;
public int getSubmitCan() {
	return submitCan;
}
public void setSubmitCan(int submitCan) {
	this.submitCan = submitCan;
}
public int getIsCancel() {
	return isCancel;
}
public void setIsCancel(int isCancel) {
	this.isCancel = isCancel;
}
public int getTaskClosed() {
	return taskClosed;
}
public void setTaskClosed(int taskClosed) {
	this.taskClosed = taskClosed;
}
public int getReSubmit() {
	return reSubmit;
}
public void setReSubmit(int reSubmit) {
	this.reSubmit = reSubmit;
}
private int reSubmit;

}
