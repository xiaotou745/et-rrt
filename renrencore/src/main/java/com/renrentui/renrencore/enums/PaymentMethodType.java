package com.renrentui.renrencore.enums;
/**
 * 任务结算方式枚举
 * @author ofmyi_000
 *
 */
public enum PaymentMethodType {
	/**
	 * 线下结算
	 */
	Offline(1, "线下结算"),
	/**
	 * 线上结算
	 */
	Online(2, "线上结算");
	private int value = 0;
	private String desc;
	private PaymentMethodType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static PaymentMethodType getEnum(int index) {
		for (PaymentMethodType c : PaymentMethodType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
