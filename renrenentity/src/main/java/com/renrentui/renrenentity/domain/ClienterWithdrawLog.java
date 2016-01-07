package com.renrentui.renrenentity.domain;

import java.util.Date;

public class ClienterWithdrawLog {
	/**
	 * 自增ID(PK)
	 */
	private Long id;

	/**
	 * 提现单ID
	 */
	private Long withwardId;

	/**
	 * 操作后状态
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 操作人
	 */
	private String operator;

	/**
	 * 操作时间
	 */
	private Date operatTime;


	/**
	 * 获取自增ID(PK)
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置自增ID(PK)
	 * @param id 自增ID(PK)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取提现单ID
	 */
	public Long getWithwardId() {
		return withwardId;
	}
	/**
	 * 设置提现单ID
	 * @param withwardId 提现单ID
	 */
	public void setWithwardId(Long withwardId) {
		this.withwardId = withwardId;
	}

	/**
	 * 获取操作后状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置操作后状态
	 * @param status 操作后状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取操作人
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * 设置操作人
	 * @param operator 操作人
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获取操作时间
	 */
	public Date getOperatTime() {
		return operatTime;
	}
	/**
	 * 设置操作时间
	 * @param operatTime 操作时间
	 */
	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}

}
