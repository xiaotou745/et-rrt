package com.renrentui.renrenentity.req;

public class PagedPartnerListReq {
	private long userId;
	private long nextId;
	private int itemsCount;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
