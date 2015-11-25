package com.renrentui.renrenentity.domain;


import com.renrentui.renrencore.enums.AuditStatus;
import com.renrentui.renrencore.enums.OrderStatus;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.util.ParseHelper;



/**
 * 合同审核实体
 * @author ofmyi_000
 *
 */
public class OrderAudit {

	private Long id;
	private String clienterName;
	private String pusher;
	private String taskTitle;
	private int taskStatus;
	private int completeNum;
	private double amount;
	private String finishTime;
	private String auditTime;
	private int compCount;
	private int auditStatus;
	private int timeAfter;
	private int orderStatus;
	private Long clienterId;
	public Long getClienterId() {
		return clienterId;
	}
	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}
	public String getOrderStatus() {
		return OrderStatus.getEnum(this.orderStatus).desc()  ;
	}
	public int getOrderStatusCode() {
		return this.orderStatus  ;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPusher() {
		return pusher;
	}
	public void setPusher(String pusher) {
		this.pusher = pusher;
	}
	public int getCompleteNum() {
		return completeNum;
	}
	public void setCompleteNum(int completeNum) {
		this.completeNum = completeNum;
	}
	public String getClienterName() {
		return clienterName;
	}
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}

	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskStatus() {
		return TaskStatus.getEnum(this.taskStatus).desc();
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	public double getAmount() {
		return amount;
	}
	public String getAmountStr() {
		return ParseHelper.digitsNum(amount, 2);
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getFinishTime() {
		if(this.finishTime==null)
			return "";
		return ParseHelper.ToDateString(this.finishTime);
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getAuditTime() {
		if(this.auditTime==null)
			return "";
		return ParseHelper.ToDateString(this.auditTime);
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public int getCompCount() {
		return compCount;
	}
	public void setCompCount(int compCount) {
		this.compCount = compCount;
	}
	public String getAuditStatus() {
		return AuditStatus.getEnum(this.auditStatus).desc();
	}
	public int getAuditStatusCode() {
		return this.auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public int getTimeAfter() {
		return timeAfter;
	}
	public void setTimeAfter(int timeAfter) {
		this.timeAfter = timeAfter;
	}
}
