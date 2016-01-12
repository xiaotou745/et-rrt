package com.renrentui.renrenentity.domain;

import java.util.Date;

public class AlipayBatchModel {
	/**
	 * 自增ID(PK)
	 */
	private Long id;

	/**
	 * 批次单号
	 */
	private String batchNo;

	/**
	 * 总提现金额
	 */
	private Double totalWithdraw;

	/**
	 * 操作笔数
	 */
	private Integer optTimes;

	/**
	 * 成功笔数
	 */
	private int successTimes;

	/**
	 * 失败笔数
	 */
	private int failTimes;

	/**
	 * 批次单状态  0打款中 1 打款完成 默认0 
	 */
	private Integer status;

	/**
	 * 批次单下属提现单号集合  多个提现单号用 ',' 分割 
	 */
	private String withdrawNos;

	/**
	 * 批次单下属提现单id集合  多个提现单id用 ',' 分割 
	 */
	private String withdrawIds;

	/**
	 * 批次单创建人
	 */
	private String createBy;

	/**
	 * 批次单创建时间 默认系统时间 
	 */
	private Date createTime;

	/**
	 * 支付宝回调时间 
	 */
	private Date callbackTime;

	/**
	 * 最后操作人
	 */
	private String lastOptUser;

	/**
	 * 最后操作时间
	 */
	private Date lastOptTime;

	/**
	 * 备注
	 */
	private String remarks;

	private String newBatchNo;
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
	 * 获取批次单号
	 */
	public String getBatchNo() {
		return batchNo;
	}
	/**
	 * 设置批次单号
	 * @param batchNo 批次单号
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * 获取总提现金额
	 */
	public Double getTotalWithdraw() {
		return totalWithdraw;
	}
	/**
	 * 设置总提现金额
	 * @param totalWithdraw 总提现金额
	 */
	public void setTotalWithdraw(Double totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}

	/**
	 * 获取操作笔数
	 */
	public Integer getOptTimes() {
		return optTimes;
	}
	/**
	 * 设置操作笔数
	 * @param optTimes 操作笔数
	 */
	public void setOptTimes(Integer optTimes) {
		this.optTimes = optTimes;
	}

	/**
	 * 获取成功笔数
	 */
	public int getSuccessTimes() {
		return successTimes;
	}
	/**
	 * 设置成功笔数
	 * @param successTimes 成功笔数
	 */
	public void setSuccessTimes(int successTimes) {
		this.successTimes = successTimes;
	}

	/**
	 * 获取失败笔数
	 */
	public int getFailTimes() {
		return failTimes;
	}
	/**
	 * 设置失败笔数
	 * @param failTimes 失败笔数
	 */
	public void setFailTimes(int failTimes) {
		this.failTimes = failTimes;
	}

	/**
	 * 获取批次单状态  0打款中 1 打款完成 默认0 
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置批次单状态  0打款中 1 打款完成 默认0 
	 * @param status 批次单状态  0打款中 1 打款完成 默认0 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取批次单下属提现单号集合  多个提现单号用 ',' 分割 
	 */
	public String getWithdrawNos() {
		return withdrawNos;
	}
	/**
	 * 设置批次单下属提现单号集合  多个提现单号用 ',' 分割 
	 * @param withdrawNos 批次单下属提现单号集合  多个提现单号用 ',' 分割 
	 */
	public void setWithdrawNos(String withdrawNos) {
		this.withdrawNos = withdrawNos;
	}

	/**
	 * 获取批次单下属提现单id集合  多个提现单id用 ',' 分割 
	 */
	public String getWithdrawIds() {
		return withdrawIds;
	}
	/**
	 * 设置批次单下属提现单id集合  多个提现单id用 ',' 分割 
	 * @param withdrawIds 批次单下属提现单id集合  多个提现单id用 ',' 分割 
	 */
	public void setWithdrawIds(String withdrawIds) {
		this.withdrawIds = withdrawIds;
	}

	/**
	 * 获取批次单创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置批次单创建人
	 * @param createBy 批次单创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取批次单创建时间 默认系统时间 
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置批次单创建时间 默认系统时间 
	 * @param createTime 批次单创建时间 默认系统时间 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取支付宝回调时间 
	 */
	public Date getCallbackTime() {
		return callbackTime;
	}
	/**
	 * 设置支付宝回调时间 
	 * @param callbackTime 支付宝回调时间 
	 */
	public void setCallbackTime(Date callbackTime) {
		this.callbackTime = callbackTime;
	}

	/**
	 * 获取最后操作人
	 */
	public String getLastOptUser() {
		return lastOptUser;
	}
	/**
	 * 设置最后操作人
	 * @param lastOptUser 最后操作人
	 */
	public void setLastOptUser(String lastOptUser) {
		this.lastOptUser = lastOptUser;
	}

	/**
	 * 获取最后操作时间
	 */
	public Date getLastOptTime() {
		return lastOptTime;
	}
	/**
	 * 设置最后操作时间
	 * @param lastOptTime 最后操作时间
	 */
	public void setLastOptTime(Date lastOptTime) {
		this.lastOptTime = lastOptTime;
	}

	/**
	 * 获取备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置备注
	 * @param remarks 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getNewBatchNo() {
		return newBatchNo;
	}
	public void setNewBatchNo(String newBatchNo) {
		this.newBatchNo = newBatchNo;
	}

}
