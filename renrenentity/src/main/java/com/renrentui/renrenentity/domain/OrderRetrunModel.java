package com.renrentui.renrenentity.domain;

import com.renrentui.renrencore.enums.GetTaskCode;

/**
 * 领取任务的时候临时用到
 * @author ofmyi_000
 *
 */
public class OrderRetrunModel {
private Long orderId;
private GetTaskCode code;
public Long getOrderId() {
	return orderId;
}
public void setOrderId(Long orderId) {
	this.orderId = orderId;
}
public GetTaskCode getCode() {
	return code;
}
public void setCode(GetTaskCode code) {
	this.code = code;
} 
}
