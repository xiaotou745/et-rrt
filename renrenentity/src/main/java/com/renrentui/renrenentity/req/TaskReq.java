package com.renrentui.renrenentity.req;

public class TaskReq {
	private int userId;
	
	private double longitude;
	
	private double latitude;
	
	private long nextId;
	
	private int itemsCount;
	
	private String cityCode;
	
	private String cityName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	 private int auditStatus;

	 /**
	  * 订单审核状态       当前任务 审核中  未通过 会用
	  * @return
	  */
	public int getAuditStatus() {
		return auditStatus;
	}
	
	/**
	 * 订单审核状态   当前任务 审核中  未通过 会用
	 * @param auditStatus
	 */
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	 
}
