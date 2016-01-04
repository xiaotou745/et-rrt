package com.renrentui.renrenentity.domain;


public class ClienterRelationModel {
private Integer clienterLevel;
private Double totalAmount;
private Double totalSubAmount;
private int clienterCount ;
private int completeCount;
private int myId;
public int getMyId() {
	return myId;
}
public void setMyId(int myId) {
	this.myId = myId;
}
public Integer getClienterLevel() {
	return clienterLevel;
}
public void setClienterLevel(Integer clienterLevel) {
	this.clienterLevel = clienterLevel;
}
public Double getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(Double totalAmount) {
	this.totalAmount = totalAmount;
}
public Double getTotalSubAmount() {
	return totalSubAmount;
}
public void setTotalSubAmount(Double totalSubAmount) {
	this.totalSubAmount = totalSubAmount;
}
public int getClienterCount() {
	return clienterCount;
}
public void setClienterCount(int clienterCount) {
	this.clienterCount = clienterCount;
}
public int getCompleteCount() {
	return completeCount;
}
public void setCompleteCount(int completeCount) {
	this.completeCount = completeCount;
}

}
