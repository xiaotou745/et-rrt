package com.renrentui.renrencore.enums;

public enum TemplateStatus {
	/**
	 * 无效
	 */
	InValid(0, "无效"),
	/**
	 * 有效
	 */
	Valid(1, "有效");


	private int value = 0;
	private String desc;
	private TemplateStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TemplateStatus getEnum(int index) {
		for (TemplateStatus c : TemplateStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
