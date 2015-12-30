package com.renrentui.renrenentity.domain;

import java.util.List;

import com.renrentui.renrenentity.ClienterBalanceRecord;

public class BalanceRecordModel {
private List<ClienterBalanceRecord> inComeList;
private List<ClienterBalanceRecord> expensesList;
public List<ClienterBalanceRecord> getInComeList() {
	return inComeList;
}
public void setInComeList(List<ClienterBalanceRecord> inComeList) {
	this.inComeList = inComeList;
}
public List<ClienterBalanceRecord> getExpensesList() {
	return expensesList;
}
public void setExpensesList(List<ClienterBalanceRecord> expensesList) {
	this.expensesList = expensesList;
}
}
