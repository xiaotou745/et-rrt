package com.renrentui.renrenentity.domain;

import java.util.Date;

public class TaskDatum {
	/**

	 * 

	 */

	private Long id;

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}



	/**

	 * 客户任务关系表Id(ClienterTask)

	 */

	private Long ctId;

	public Long getCtId() {

		return ctId;

	}

	public void setCtId(Long ctId) {

		this.ctId = ctId;

	}



	/**

	 * 地推员ID

	 */

	private Long clienterId;

	public Long getClienterId() {

		return clienterId;

	}

	public void setClienterId(Long clienterId) {

		this.clienterId = clienterId;

	}



	/**

	 * 任务ID

	 */

	private Long taskId;

	public Long getTaskId() {

		return taskId;

	}

	public void setTaskId(Long taskId) {

		this.taskId = taskId;

	}



	/**

	 * 创建时间(资料提交时间)

	 */

	private Date createDate;

	public Date getCreateDate() {

		return createDate;

	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;

	}



	/**

	 * 资料状态 1 待审核 2 审核通过 3 审核拒绝

	 */

	private Integer status;

	public Integer getStatus() {

		return status;

	}

	public void setStatus(Integer status) {

		this.status = status;

	}



	/**

	 * 审核时间

	 */

	private Date auditTime;

	public Date getAuditTime() {

		return auditTime;

	}

	public void setAuditTime(Date auditTime) {

		this.auditTime = auditTime;

	}



	/**

	 * 审核人

	 */

	private String auditName;

	public String getAuditName() {

		return auditName;

	}

	public void setAuditName(String auditName) {

		this.auditName = auditName;

	}



	/**

	 * 状态为审核拒绝时,拒绝原因

	 */

	private String refuseReason;

	public String getRefuseReason() {

		return refuseReason;

	}

	public void setRefuseReason(String refuseReason) {

		this.refuseReason = refuseReason;

	}



}