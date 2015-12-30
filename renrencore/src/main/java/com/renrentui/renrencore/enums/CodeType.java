package com.renrentui.renrencore.enums;

public enum CodeType {
	/**
	 * 注册
	 */
	Register(1, "注册"),
	/**
	 * 修改密码
	 */
	UpdatePasswrd(2, "修改密码"),
	/**
	 * 忘记密码
	 */
	ForgetPassword(3, "忘记密码"),
	/**
	 * 绑定支付宝
	 */
	BindAliPay(4, "绑定支付宝");
	private int value = 0;
	private String desc;
	private CodeType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static CodeType getEnum(int index) {
		for (CodeType c : CodeType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
