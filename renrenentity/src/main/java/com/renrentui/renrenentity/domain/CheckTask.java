package com.renrentui.renrenentity.domain;
/**
 * 验证任务是否可以领取
 * @author ofmyi_000
 *
 */
public class CheckTask {
private int blanceCan;
private int endTimeCan;
private int orderCan;
private double taskCycle;
private double amount;
private int countCan;
public int getCountCan() {
	return countCan;
}
public void setCountCan(int countCan) {
	this.countCan = countCan;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public double getTaskCycle() {
	return taskCycle;
}
public void setTaskCycle(double taskCycle) {
	this.taskCycle = taskCycle;
}
public int getBlanceCan() {
	return blanceCan;
}
public void setBlanceCan(int blanceCan) {
	this.blanceCan = blanceCan;
}
public int getEndTimeCan() {
	return endTimeCan;
}
public void setEndTimeCan(int endTimeCan) {
	this.endTimeCan = endTimeCan;
}
public int getOrderCan() {
	return orderCan;
}
public void setOrderCan(int orderCan) {
	this.orderCan = orderCan;
}
}
