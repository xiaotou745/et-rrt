package com.renrentui.renrencore.enums;

public enum SignUpCode {

	/**
	 * 成功
	 */
	Success(200, "success"),
	/**
	 * 验证码不正确
	 */
	VerCodeError(1011, "验证码不正确"),
	/**
	 * 验证码空
	 */
	VerCodeNull(1012, "验证码不不能为空"),
	/**
	 * 手机号格式不正确
	 */
	PhoneFormatError(1013, "手机号已注册过"),
	/**
	 * 手机号不能为空
	 */
	PhoneNull(1014, "手机号不能为空"),
	/*
	 * 手机号已存在
	 */
	PhoneHadExist(1015, "手机号不能为空"),
	/*
	 * 系统错误
	 */
	SystemError(1016, "系统错误"), 
	/**
	 * 
	 */
	Fail(1017, "注册失败");

	private int value = 0;
	private String desc;
	private SignUpCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SignUpCode getEnum(int index) {
		for (SignUpCode c : SignUpCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}