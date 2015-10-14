package com.renrentui.renrencore.enums;

public enum BusinessRechargePayStatus {

	/**
	 * 待支付
	 */
	Tobepaid(0, "待支付"),
	/**
	 * 已支付
	 */
	Paid(1, "已支付");


	private int value = 0;
	private String desc;
	private BusinessRechargePayStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static BusinessRechargePayStatus getEnum(int index) {
		for (BusinessRechargePayStatus c : BusinessRechargePayStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
