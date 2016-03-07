package com.renrentui.renrencore.enums;

public enum ActivityEnum {
 
	BindWeiXinFetchRedBag(1,"绑定微信领奖励");
	
	private int value = 0;
	private String desc;
	private ActivityEnum(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ActivityEnum getEnum(int index) {
		for (ActivityEnum c : ActivityEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
