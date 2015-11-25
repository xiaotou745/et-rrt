package com.renrentui.renrenentity.req;

public class TaskReq {
	private int userId;
	
	private long nextId;
	
	private int itemsCount;
	
	private long cityCode;
	private long provinceCode;
	
	private int taskStatus;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public long getCityCode() {
		return cityCode;
	}

	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public long getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(long provinceCode) {
		this.provinceCode = provinceCode;
	}
}
