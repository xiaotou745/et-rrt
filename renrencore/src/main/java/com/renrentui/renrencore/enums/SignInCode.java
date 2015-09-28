package com.renrentui.renrencore.enums;

public enum SignInCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	/**
	 * 手机号或密码不正确
	 */
	PhoneOrPwdError(1021, "手机号或密码不正确"),
	/**
	 * 手机号或密码不能为空
	 */
	PhoneOrPwdNull(1022, "手机号或密码不能为空"),
	/**
	 * 手机号未注册
	 */
	PhoneUnRegistered(1023, "手机号未注册"),
	/**
	 * 
	 */
	Fail(1024, "系统错误");
	private int value = 0;
	private String desc;
	private SignInCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SignInCode getEnum(int index) {
		for (SignInCode c : SignInCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
