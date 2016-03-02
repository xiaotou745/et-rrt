package com.renrentui.renrencore.enums;

public enum FetchRedbagEnum {
	/**
	 * 成功
	 */
	Success(1000, "领取成功"),
	BindFail(1010,"绑定失败"),
	/**
	 * 验证码不正确
	 */
	VerCodeError(1011, "验证码不正确"),
	ParaError(1012,"参数非法"),
	PhoneNoError(1013,"手机号为空"),
	PhoneNotRegister(1014, "手机号未注册"),
	HadBindThisActivity(1015,"已绑定过，领取失败"),
	NoAttentionWx(1016,"未关注微信公众号"),
	HadFetchRedbag(1018,"已领过活动奖励"),
	Fail(1017, "领取失败");
	private int value = 0;
	private String desc;
	private FetchRedbagEnum(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static FetchRedbagEnum getEnum(int index) {
		for (FetchRedbagEnum c : FetchRedbagEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
