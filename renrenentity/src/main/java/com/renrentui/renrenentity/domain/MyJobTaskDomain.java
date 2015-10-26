package com.renrentui.renrenentity.domain;

/**
 * 骑士  当前任务 
 * @author CaoHeYang
 * @date 20151026
 */
public class MyJobTaskDomain extends TaskDomain {

	private int receivedCount;
	private int passCount;
	private int noPassCount;
	/**
	 * 已领取数量
	 */
	public int getReceivedCount() {
		return receivedCount;
	}
	/**
	 *  已领取数量
	 * @param receivedCount
	 */
	public void setReceivedCount(int receivedCount) {
		this.receivedCount = receivedCount;
	}
	/**
	 * 审核中数量
	 * @return
	 */
	public int getPassCount() {
		return passCount;
	}
	/**
	 * 审核中数量
	 * @param passCount
	 */
	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}
	/**
	 * 未通过数量
	 * @return
	 */
	public int getNoPassCount() {
		return noPassCount;
	}
	/**
	 * 未通过数量
	 * @param noPassCount
	 */
	public void setNoPassCount(int noPassCount) {
		this.noPassCount = noPassCount;
	}
}
