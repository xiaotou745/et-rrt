package com.renrentui.renrencore.enums;
/**
 * 
 * 取消任务接口返回码
 * @author ofmyi_000
 *
 */

public enum CancelTaskCode {
	/**
	 * 成功
	 */
	Success(200, "success"),

	TaskComplete(1151, "任务已经完成不能取消"),
	TaskIsCancel(1156, "订单已经取消"),
	OrderIdErr(1152, "订单Id有误"),
	UserIdErr(1153, "用户ID有误"),
	OrderNull(1154, "订单不存在"),
	CantCancel(1157, "订单不可取消"),
	Fail(1155, "取消失败");

	private int value = 0;
	private String desc;
	private CancelTaskCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static CancelTaskCode getEnum(int index) {
		for (CancelTaskCode c : CancelTaskCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
