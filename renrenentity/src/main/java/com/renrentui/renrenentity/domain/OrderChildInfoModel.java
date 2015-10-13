package com.renrentui.renrenentity.domain;

import java.util.ArrayList;

public class OrderChildInfoModel {
	public OrderChildInfoModel()
	{
		this.list=new ArrayList<OrderChildModel> ();
	}
private String taskTitle ;
private String clienterName ;
private String companyName ;
private ArrayList<OrderChildModel> list;
public String getTaskTitle() {
	return taskTitle;
}
public void setTaskTitle(String taskTitle) {
	this.taskTitle = taskTitle;
}
public String getClienterName() {
	return clienterName;
}
public void setClienterName(String clienterName) {
	this.clienterName = clienterName;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public ArrayList<OrderChildModel> getList() {
	return list;
}
public void setList(ArrayList<OrderChildModel> list) {
	this.list = list;
}
}
