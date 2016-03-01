package com.renrentui.renrenentity.req;

import java.util.Date;
import java.util.List;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class PagedRenRenTaskReq extends PagedRequestBase{
    private String taskTitle;
	private String pusher;
    private String createTimeBegin;
    private String createTimeEnd;
    private String beginTime;
    private String endTime;
    private Integer status;
    private Integer taskType;
    private long cityCode;
    private String cityName;
    private List<Integer> areaCodeList;
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getPusher() {
		return pusher;
	}
	public void setPusher(String pusher) {
		this.pusher = pusher;
	}
	public String getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public long getCityCode() {
		return cityCode;
	}
	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<Integer> getAreaCodeList() {
		return areaCodeList;
	}
	public void setAreaCodeList(List<Integer> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}

	
	
}
