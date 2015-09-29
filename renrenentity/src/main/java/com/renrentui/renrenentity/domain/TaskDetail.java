package com.renrentui.renrenentity.domain;
/**
 * 任务详情实体 
 * @author ofmyi_000
 *
 */
public class TaskDetail {
	
	public  TaskDetail() {
		this.contractInfo=new ContractInfo();
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
	private Long tempateId;
	private ContractInfo contractInfo;
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
	public int getPaymentMethod() {
		return paymentMethod;
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
	public Long getTempateId() {
		return tempateId;
	}
	public void setTempateId(Long tempateId) {
		this.tempateId = tempateId;
	}
	public ContractInfo getContractInfo() {
		return contractInfo;
	}
	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}
	
}
