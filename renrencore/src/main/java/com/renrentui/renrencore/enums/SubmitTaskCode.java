package com.renrentui.renrencore.enums;
/**
 * 提交任务枚举
 * @author ofmyi_000
 *
 */
public enum SubmitTaskCode {
	/**
	 * 成功
	 */
	Success(200, "success"),

	OrderCancel(1161, "该订单已取消"),

	CantSubmit(1162, "该任务不能提交"),

	TaskClosed(1163, "该任务已经关闭"),
	
	ReSubmit(1165, "任务待审核不可重复提交"),
	UserIdError(1166, "用户ID错误"),
	CtIdError(1167, "CtID错误"),
	TaskIdError(1168, "TaskID错误"),
	ValueInfoNull(1169, "资料数据最少有一项"),
	Fail(1164, "提交失败");

	private int value = 0;
	private String desc;
	private SubmitTaskCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SubmitTaskCode getEnum(int index) {
		for (SubmitTaskCode c : SubmitTaskCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
