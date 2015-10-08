package com.renrentui.renrenentity.domain;

import java.util.ArrayList;

import com.renrentui.renrencore.enums.PaymentMethodType;

/**
 * 任务详情实体 
 * @author ofmyi_000
 *
 */
public class TaskDetail {
	
	public TaskDetail()
	{
		this.controlInfo=new ArrayList<ControlInfo>();
	}
	private Long id;
	private String taskTitle;
	private String taskNotice;
	private double amount;
	private String endTime;
	private int availableCount;
	private int paymentMethod;
	private String taskGeneralInfo;
	private String taskNote;
	private Long businessId;
	private Long templateId;
	private String pusher;
	private String templateName;
	private ArrayList<ControlInfo> controlInfo;
	private int isHad;
	private Long orderId;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public int getIsHad() {
		return isHad;
	}
	public void setIsHad(int isHad) {
		this.isHad = isHad;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getPusher() {
		return pusher;
	}
	public void setPusher(String pusher) {
		this.pusher = pusher;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskNotice() {
		return taskNotice;
	}
	public void setTaskNotice(String taskNotice) {
		this.taskNotice = taskNotice;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}
	public String getPaymentMethod() {
		return PaymentMethodType.getEnum(paymentMethod).desc() ;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getTaskGeneralInfo() {
		return taskGeneralInfo;
	}
	public void setTaskGeneralInfo(String taskGeneralInfo) {
		this.taskGeneralInfo = taskGeneralInfo;
	}
	public String getTaskNote() {
		return taskNote;
	}
	public void setTaskNote(String taskNote) {
		this.taskNote = taskNote;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public ArrayList<ControlInfo> getControlInfo() {
		return controlInfo;
	}
	public void setControlInfo(ArrayList<ControlInfo> controlInfo) {
		this.controlInfo = controlInfo;
	}
	
}
