package com.renrentui.renrencore.enums;

public enum TaskStatus {
	/**
	 * 待审核
	 */
	WaitAudit(0, "待审核"),
	/**
	 * 审核通过
	 */
	Audited(1, "审核通过"),
	/**
	 * 驳回
	 */
	Reject(2, "驳回"),
	/**
	 * 过期
	 */
	Expired(3, "过期"),
	/**
	 * 终止
	 */
	Stop(4, "终止");

	private int value = 0;
	private String desc;
	private TaskStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TaskStatus getEnum(int index) {
		for (TaskStatus c : TaskStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
