package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class PagedTemplateReq extends PagedRequestBase{
private String templateName;
private String createName;
private String createTimeBegin;
private String createTimeEnd;
private Long businessId;
private int status;
public String getTemplateName() {
	return templateName;
}
public void setTemplateName(String templateName) {
	this.templateName = templateName;
}
public String getCreateName() {
	return createName;
}
public void setCreateName(String createName) {
	this.createName = createName;
}
public Long getBusinessId() {
	return businessId;
}
public void setBusinessId(Long businessId) {
	this.businessId = businessId;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
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
