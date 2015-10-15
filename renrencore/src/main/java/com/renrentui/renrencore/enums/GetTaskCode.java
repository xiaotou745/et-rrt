package com.renrentui.renrencore.enums;
/**
 * 领取任务返回值
 * @author ofmyi_000
 *
 */
public enum GetTaskCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	UserIdErr(1141, "用户ID错误"),
	TaskIdErr(1142, "任务ID错误"),
	TaskExpire(1143, "任务过期或不存在"),
	TaskHad(1144, "该任务已领取且尚未完成"),
	TaskNoBlance(1145, "任务余量不足"),
	TaskMore(1147, "您当前领取的任务够多了,先完成一些吧"),
	Fail(1146, "系统错误");
	private int value = 0;
	private String desc;
	private GetTaskCode(int value, String desc) { 
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
