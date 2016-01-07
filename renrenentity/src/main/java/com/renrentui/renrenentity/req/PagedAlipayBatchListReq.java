package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class PagedAlipayBatchListReq extends PagedRequestBase {
	private String batchNo;
	private Integer status;
	private  String lastOptTimeStart;
	private  String lastOptTimeEnd;
	/**
	 * 批次号
	 * @return
	 */
	public String getBatchNo() {
		return batchNo;
	}
	/**
	 *  批次号
	 * @param batchNo
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	/**
	 * 批次单状态
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 批次单状态
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 打款时间 开始时间
	 * @return
	 */
	public String getLastOptTimeStart() {
		return lastOptTimeStart;
	}
	/**
	 * 打款时间 开始时间
	 * @param lastOptTimeStart
	 */
	public void setLastOptTimeStart(String lastOptTimeStart) {
		this.lastOptTimeStart = lastOptTimeStart;
	}
	/**
	 * 打款时间 结束时间
	 * @return
	 */
	public String getLastOptTimeEnd() {
		return lastOptTimeEnd;
	}
	/**
	 *  打款时间 结束时间
	 * @param lastOptTimeEnd
	 */
	public void setLastOptTimeEnd(String lastOptTimeEnd) {
		this.lastOptTimeEnd = lastOptTimeEnd;
	}
	}