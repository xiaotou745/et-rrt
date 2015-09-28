package com.renrentui.core.enums;
/**
 * 
 * C端忘记密码枚举
 * @author ofmyi_000
 *
 */
public enum ForgotPwdCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	/**
	 * 验证码不正确
	 */
	VerCodeError(1031, "验证码不正确"),
	/**
	 * 验证码空
	 */
	VerCodeNull(1032, "验证码不不能为空"),
	/**
	 * 手机号不正确
	 */
	PhoneError(1033, "手机号不正确"),
	/**
	 * 手机号不能为空
	 */
	PhoneNull(1034, "手机号不能为空"),
	/**
	 * 
	 */
	Fail(1035, "设置失败");

	private int value = 0;
	private String desc;
	private ForgotPwdCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ForgotPwdCode getEnum(int index) {
		for (ForgotPwdCode c : ForgotPwdCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
