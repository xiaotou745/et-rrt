package com.renrentui.renrenentity.req;

public class CancelOrderReq {
private Long orderId;
private String optName;
private String remark;
public Long getOrderId() {
	return orderId;
}
public void setOrderId(Long orderId) {
	this.orderId = orderId;
}
public String getOptName() {
	return optName;
}
public void setOptName(String optName) {
	this.optName = optName;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
}
