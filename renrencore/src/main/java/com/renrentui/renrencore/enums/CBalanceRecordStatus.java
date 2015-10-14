package com.renrentui.renrencore.enums;

public enum CBalanceRecordStatus {
	


	/**
	 * 交易成功
	 */
	Success(1, "交易成功"),
	/**
	 * 交易中
	 */
	Trading(2, "交易中");
	
	private int value = 0;
	private String desc;
	private CBalanceRecordStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static CBalanceRecordStatus getEnum(int index) {
		for (CBalanceRecordStatus c : CBalanceRecordStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
