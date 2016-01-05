package com.renrentui.renrencore.enums;
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
	Fail(1035, "设置失败"),
	/**
	 * 支付宝账号不能为空
	 */
	AliAccountNull(1036, "支付宝账号不能为空"),
	/**
	 * 支付宝账号实名不能为空
	 */
	AliNameNull(1037, "支付宝账号实名不能为空"),
	/**
	 * userid和Phone不匹配
	 */
	OtherPhone(1038, "userid和Phone不匹配"),
	/**
	 * userid不能<=0
	 */
	UserIdError(1039, "userid不能<=0");

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
