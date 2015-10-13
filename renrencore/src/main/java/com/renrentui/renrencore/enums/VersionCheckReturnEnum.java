package com.renrentui.renrencore.enums;

/**
 * 版本升级检测返回值枚举
 * 
 * @author CaoHeYang
 * @date 20151013
 */
public enum VersionCheckReturnEnum {
	/**
	 * 获取成功
	 */
	Success(200, "获取成功"),
	/**
	 * 调用第三方友盟
	 */
	ThirdParty(1901, "调用第三方友盟"),
	/**
	 * .获取失败
	 */
	Failed(1902, "获取失败"),
	/**
	 * 缺少UserType参数
	 */
	NoUserType(1903, "缺少UserType参数"),
	/**
	 *缺少PlatForm参数
	 */
	NoPlatForm(1904, "缺少PlatForm参数"),
	/**
	 * 暂无数据
	 */
	NoData(1905, "暂无数据");
	private int value = 0;
	private String desc;

	private VersionCheckReturnEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static VersionCheckReturnEnum getEnum(int index) {
		for (VersionCheckReturnEnum c : VersionCheckReturnEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
