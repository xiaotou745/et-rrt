package com.renrentui.renrencore.enums;

public enum BusinessRechargePayType {
	/**
	 * 支付宝
	 */
	Alipay(1, "支付宝"),
	/**
	 * 微信
	 */
	WeChat(2, "微信"),
	/**
	 * 后台
	 */
	Backstage(3, "后台"),
	/**
	 * 赠送
	 */
	Give(4, "赠送");

	private int value = 0;
	private String desc;
	private BusinessRechargePayType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static BusinessRechargePayType getEnum(int index) {
		for (BusinessRechargePayType c : BusinessRechargePayType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
