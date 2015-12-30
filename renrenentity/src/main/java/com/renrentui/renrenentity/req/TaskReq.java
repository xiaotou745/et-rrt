package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;



public class TaskReq extends PagedRequestBase{
	private int userId;
	
	private long cityCode;
	private long provinceCode;
	
	private int taskStatus;
	private int orderBy;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
}
