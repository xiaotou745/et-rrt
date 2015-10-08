package com.renrentui.renrencore.enums;

/**
 *  C端修改个人基础信息 返回状态码
 * @author CaoHeYang
 * @date 20151008
 */
public enum ModifyUserCReturnCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	/**
	 * 年龄不合法
	 */
	AgeError(1901, "年龄不合法"),
	/**
	 *性别有误
	 */
	SexError(1902, "性别有误"),
	/**
	 * 名称有误
	 */
	UserNameError(1903, "地推员姓名有误"),
	/**
	 * 当前骑士不存在
	 */
	UserError(1905, "当前地推员不存在");

	private int value = 0;
	private String desc;
	private ModifyUserCReturnCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ModifyUserCReturnCode getEnum(int index) {
		for (ModifyUserCReturnCode c : ModifyUserCReturnCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
