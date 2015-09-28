package com.renrentui.renrencore.enums;

public enum WithdrawState  {
	/**
	 * Success
	 */
	Success(200, "Success"),
	/**
	 * 提取金额大于可提现金额
	 */
	MoneyError(1211, "提取金额大于可提现金额"),
	/**
	 * 提现失败
	 */
	Failure(1212, "提现失败");

	private int value = 0;
	private String desc;
	private WithdrawState(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static WithdrawState getEnum(int index) {
		for (WithdrawState c : WithdrawState.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}

