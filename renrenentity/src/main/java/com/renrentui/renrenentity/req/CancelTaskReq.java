package com.renrentui.renrenentity.req;
/**
 * 取消任务实体类
 * @author ofmyi_000
 *
 */
public class CancelTaskReq {

	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private Long orderId;
	private Long userId;
	private String remark;
}	
