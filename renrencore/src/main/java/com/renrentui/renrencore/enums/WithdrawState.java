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
	MoreThenTen(1212, "提现金额不能小于10元"),
	LessThenOneThousand(1213, "单笔提现金额不能超过1000元"),
	MustIntegralMultiple(1214, "提现金额必须为10的整数倍"),
	UserIDError(1215, "userid必须大于0"),
	LessTen(1214,"提现金额不能小于10元"),
	MoreThousand(1215,"单笔提现金额不能超过1000元"),
	NoTenMultiple(1216,"提现金额必须为10的整数倍"),
	/**
	 * 提现失败
	 */
	Failure(1312, "提现失败");

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

