package com.renrentui.renrencore.enums;

public enum BBalanceRecordType {
	/**
	 * 发布任务
	 */
	ReleaseTask(1, "待审核"),
	/**
	 * 充值
	 */
	Delta(2, "充值");
	
	private int value = 0;
	private String desc;
	private BBalanceRecordType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static BBalanceRecordType getEnum(int index) {
		for (BBalanceRecordType c : BBalanceRecordType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
