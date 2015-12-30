package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

/**
 * 合同审核请求参数
 * 茹化肖
 * @author ofmyi_000
 *
 */
public class PagedAuditorderReq extends PagedRequestBase{
	private String clienterPhone;
	private String taskName;
	private String beginDate;
	private String endDate;
	public String getClienterPhone() {
		return clienterPhone;
	}
	public void setClienterPhone(String clienterPhone) {
		this.clienterPhone = clienterPhone;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	private String clienterName;
	public String getClienterName() {
		return clienterName;
	}
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}
	private String orderNo;
	private String companyName;
	
	private int auditStatus;

	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
