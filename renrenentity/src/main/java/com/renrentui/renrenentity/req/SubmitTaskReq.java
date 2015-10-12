package com.renrentui.renrenentity.req;

import java.util.ArrayList;

import com.renrentui.renrenentity.domain.SubmitValue;

/**
 * 提交订单接口
 * @author ofmyi_000
 *
 */
public class SubmitTaskReq {
	private Long userId;
	private Long orderId;
	private Long templateId;
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	private ArrayList<SubmitValue> valueInfo;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public ArrayList<SubmitValue> getValueInfo() {
		return valueInfo;
	}
	public void setValueInfo(ArrayList<SubmitValue> valueInfo) {
		this.valueInfo = valueInfo;
	}
}
