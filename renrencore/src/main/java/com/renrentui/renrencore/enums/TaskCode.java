package com.renrentui.renrencore.enums;

public enum TaskCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	GetTaskFail(1101, "获取任务失败"),
	UserIdErr(1102, "用户ID错误"),
	OrderType(1103, "任务类型不能为空"),  
	Fail(1146, "系统错误");
	private int value = 0;
	private String desc;
	private TaskCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static GetTaskCode getEnum(int index) {
		for (GetTaskCode c : GetTaskCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
