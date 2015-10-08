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
}
