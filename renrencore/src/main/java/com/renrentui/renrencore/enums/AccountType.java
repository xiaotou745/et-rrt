package com.renrentui.renrencore.enums;

public enum AccountType {
	/**
	 * 网银
	 */
	NetBank(1, "网银"),
	/**
	 * 支付宝
	 */
	AliPay(2, "支付宝"),
	/**
	 * 微信
	 */
	WeiXin(3, "微信"),
	/**
	 * 财付通
	 */
	CaifuTong(4, "财付通"),
	/**
	 * 百度钱包
	 */
	BaiDuQianBao(5, "百度钱包");
	private int value = 0;
	private String desc;
	private AccountType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static AccountType getEnum(int index) {
		for (AccountType c : AccountType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
