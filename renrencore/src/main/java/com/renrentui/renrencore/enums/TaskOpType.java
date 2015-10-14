package com.renrentui.renrencore.enums;

public enum TaskOpType {
	/**
	 * 新建
	 */
	NewTask(0, "新建"),
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
	 * 中止
	 */
	Stop(4, "中止"),
	/**
	 * 修改任务
	 */
	Modify(5, "修改任务"),
	/**
	 * 取消任务
	 */
	CancelTask(6, "取消任务");

	private int value = 0;
	private String desc;
	private TaskOpType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TaskOpType getEnum(int index) {
		for (TaskOpType c : TaskOpType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
