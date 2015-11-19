package com.renrentui.renrenentity.domain;
/**
 * 验证任务是否可以领取
 * @author ofmyi_000
 *
 */
public class CheckTask {
private Long businessId;
private Double amount;
private Integer taskType;
public Long getBusinessId() {
	return businessId;
}
public void setBusinessId(Long businessId) {
	this.businessId = businessId;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public Integer getTaskType() {
	return taskType;
}
public void setTaskType(Integer taskType) {
	this.taskType = taskType;
}
}
