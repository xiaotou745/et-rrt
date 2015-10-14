package com.renrentui.renrenentity.domain;

import com.renrentui.renrenentity.RenRenTask;

public class RenRenTaskModel extends RenRenTask{
    private String businessName;
    private String templateName;
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
	private  int CanSettlement;
	  /**
	   *  是否可以结算 1 可以 0 不可以 
	   * @return
	   */
		public int getCanSettlement() {
			return CanSettlement;
		}
	/**
	 * 是否可以结算 1 可以 0 不可以 
	 * @param canSettlement
	 */
		public void setCanSettlement(int canSettlement) {
			CanSettlement = canSettlement;
		}
}
