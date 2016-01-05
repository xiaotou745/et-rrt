package com.renrentui.renrencore.enums;

public enum CBalanceRecordType {
	
	/**
	 * 佣金
	 */
	Commission(1, "佣金"),
	/**
	 * 提现申请
	 */
	ApplicationFor(2, "提现申请"),

	/**
	 * 提现拒绝
	 */
	DenialOf(3, "提现拒绝"),
	/**
	 * 提现失败
	 */
	WithdrawFail(4, "提现失败"),
	/**
	 * 合伙人分红
	 */
	Bonus(5, "合伙人分红");
	
	private int value = 0;
	private String desc;
	private CBalanceRecordType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static CBalanceRecordType getEnum(int index) {
		for (CBalanceRecordType c : CBalanceRecordType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
