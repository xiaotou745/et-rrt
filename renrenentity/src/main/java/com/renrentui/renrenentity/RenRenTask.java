package com.renrentui.renrenentity;


import java.util.Date;

public class RenRenTask {
    private Long id;

    private String taskTitle;

    private String taskNotice;

    private String taskGeneralInfo;

    private Long businessId;

    private String pusher;

    private String createName;

    private Date createTime;

    private String modifyName;

    private Date modifyTime;

    private Date beginTime;

    private Date endTime;

    private Integer taskCycle;

    private Integer availableCount;

    private Double amount;

    private Integer status;

    private Integer taskTotalCount;

    private Long snapshotTemplateId;
    private String link;

    private Short paymentMethod;
    private Integer auditCycle;

    private String taskNote;
    private Long targetPeople;
    private String companySummary;
    public Integer getAuditCycle() {
		return auditCycle;
	}

	public void setAuditCycle(Integer auditCycle) {
		this.auditCycle = auditCycle;
	}

	public String getTaskNote() {
		return taskNote;
	}

	public void setTaskNote(String taskNote) {
		this.taskNote = taskNote;
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
        this.pusher = pusher == null ? null : pusher.trim();
    }

    

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
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

	public String getTaskGeneralInfo() {
		return taskGeneralInfo;
	}

	public void setTaskGeneralInfo(String taskGeneralInfo) {
		this.taskGeneralInfo = taskGeneralInfo;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getTaskCycle() {
		return taskCycle;
	}

	public void setTaskCycle(Integer taskCycle) {
		this.taskCycle = taskCycle;
	}

	public Integer getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Integer availableCount) {
		this.availableCount = availableCount;
	}

	public Short getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Short paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Long getTargetPeople() {
		return targetPeople;
	}

	public void setTargetPeople(Long targetPeople) {
		this.targetPeople = targetPeople;
	}

	public String getCompanySummary() {
		return companySummary;
	}

	public void setCompanySummary(String companySummary) {
		this.companySummary = companySummary;
	}

	public Integer getTaskTotalCount() {
		return taskTotalCount;
	}

	public void setTaskTotalCount(Integer taskTotalCount) {
		this.taskTotalCount = taskTotalCount;
	}
	/**
	 * 快照模板id
	 * @param templateId
	 */
	public Long getSnapshotTemplateId() {
		return snapshotTemplateId;
	}
	/**
	 * 快照模板id
	 * @param templateId
	 */
	public void setSnapshotTemplateId(Long snapshotTemplateId) {
		this.snapshotTemplateId = snapshotTemplateId;
	} 
}