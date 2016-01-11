package com.renrentui.renrenentity.req;

public class GetIncomeReq {
	private long userId;//用户Id
	private long recordType;
	private long nextId;
	private int itemsCount;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getRecordType() {
		return recordType;
	}
	public void setRecordType(long recordType) {
		this.recordType = recordType;
	}
	public long getNextId() {
		return nextId;
	}
	public void setNextId(long nextId) {
		this.nextId = nextId;
	}
	public int getItemsCount() {
		return itemsCount;
	}
	public void setItemsCount(int itemsCount) {
		this.itemsCount = itemsCount;
	}
}
