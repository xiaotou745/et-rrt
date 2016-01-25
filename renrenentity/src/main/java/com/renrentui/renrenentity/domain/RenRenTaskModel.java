package com.renrentui.renrenentity.domain;

import com.renrentui.renrenentity.RenRenTask;

public class RenRenTaskModel extends RenRenTask{
    private String businessName;
    private String templateName;
	//private  Integer canSettlement;
    private Integer completeNum;
	public Integer getCompleteNum() {
		return completeNum;
	}
	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
//	/**
//	 * 是否可以结算 1 可以 0 不可以 
//	 * @param canSettlement
//	 */
//	public Integer getCanSettlement() {
//		return canSettlement;
//	}
//	/**
//	 * 是否可以结算 1 可以 0 不可以 
//	 * @param canSettlement
//	 */
//	public void setCanSettlement(Integer canSettlement) {
//		this.canSettlement = canSettlement;
//	}
}
