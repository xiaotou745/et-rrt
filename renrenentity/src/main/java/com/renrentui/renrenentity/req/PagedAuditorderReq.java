package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

/**
 * 合同审核请求参数
 * 茹化肖
 * @author ofmyi_000
 *
 */
public class PagedAuditorderReq extends PagedRequestBase{

	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	private Long orderId;
	private String companyName;
	
	private int auditStatus;

	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
}
