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
	 * 有效
	 */
	Valid(2, "有效"),
	/**
	 * 无效
	 */
	InValid(3, "无效"),
	/**
	 * 暂停
	 */
	Pause(4, "暂停");

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
