package com.renrentui.renrencore.enums;

public enum MyIncomeCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	/**
	 * 用户ID不存在
	 */
	UserIdUnexist(1201, "用户ID不存在"),
	/**
	 * 获取收入信息失败
	 */
	QueryIncomeError(1202, "获取收入信息失败");
	private int value = 0;
	private String desc;
	private MyIncomeCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static MyIncomeCode getEnum(int index) {
		for (MyIncomeCode c : MyIncomeCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
