package com.renrentui.renrenentity.domain;
/**
 * 验证订单是否可以取消
 * @author ofmyi_000
 *
 */
public class CheckCancelOrder {
private int cancelCan;//1 可以取消 0  不可以 
private int isComplete;//1  订单已完成
private int IsCancle;// 1 订单已取消
private String orderNo;
private Long taskId;
public Long getTaskId() {
	return taskId;
}
public void setTaskId(Long taskId) {
	this.taskId = taskId;
}
public String getOrderNo() {
	return orderNo;
}
public void setOrderNo(String orderNo) {
	this.orderNo = orderNo;
}
public int getCancelCan() {
	return cancelCan;
}
public void setCancelCan(int cancelCan) {
	this.cancelCan = cancelCan;
}
public int getIsComplete() {
	return isComplete;
}
public void setIsComplete(int isComplete) {
	this.isComplete = isComplete;
}
public int getIsCancle() {
	return IsCancle;
}
public void setIsCancle(int isCancle) {
	IsCancle = isCancle;
}
}
