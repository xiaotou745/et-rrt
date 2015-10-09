package com.renrentui.renrenentity.req;

import java.util.Date;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class PagedRenRenTaskReq extends PagedRequestBase{
    private String taskTitle;

    private Long businessId;
	private String pusher;

    private String createName;

    private String createTimeBegin;
    private String createTimeEnd;

    private String beginTime;

    private String endTime;

    private Integer status;

    private Long templateId;

    private Short paymentMethod;

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getPusher() {
		return pusher;
	}

	public void setPusher(String pusher) {
		this.pusher = pusher;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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


	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Short getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Short paymentMethod) {
		this.paymentMethod = paymentMethod;
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
}
