package com.renrentui.renrencore.enums;

public enum MyRecordCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	/**
	 * 用户ID不存在
	 */
	UserIdInValid(1201, "用户ID无效"),
	/**
	 * RecordType值无效：1是收入，2是支出
	 */
	RecordTypeError(1203, "RecordType值无效：1是收入，2是支出"),
	/**
	 * 用户ID不存在
	 */
	UserIdUnexist(1202, "用户ID不存在");
	private int value = 0;
	private String desc;
	private MyRecordCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static MyRecordCode getEnum(int index) {
		for (MyRecordCode c : MyRecordCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
