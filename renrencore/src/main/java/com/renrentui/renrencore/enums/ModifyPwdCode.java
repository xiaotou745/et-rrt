package com.renrentui.renrencore.enums;

public enum ModifyPwdCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	/**
	 * 旧密码不正确
	 */
	OldPwdError(1041, "旧密码不正确"),
	/**
	 * 
	 */
	Fail(1045, "设置失败");

	private int value = 0;
	private String desc;
	private ModifyPwdCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ModifyPwdCode getEnum(int index) {
		for (ModifyPwdCode c : ModifyPwdCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
