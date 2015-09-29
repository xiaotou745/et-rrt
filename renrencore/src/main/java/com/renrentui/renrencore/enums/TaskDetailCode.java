package com.renrentui.renrencore.enums;

public enum TaskDetailCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	UserIdErr(1102, "用户ID错误"),
	TaskIdErr(1103, "任务ID错误"),
	Fail(1101, "系统错误");
	private int value = 0;
	private String desc;
	private TaskDetailCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TaskDetailCode getEnum(int index) {
		for (TaskDetailCode c : TaskDetailCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
