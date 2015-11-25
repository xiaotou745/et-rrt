package com.renrentui.renrencore.enums;

/**
 * 任务模板中组的类型枚举
 * @author hailongzhao
 * @date 20151125
 */
public enum TemplateGroupType {
	/**
	 * 文本组
	 */
	TextGroup(1, "文本组"),
	/**
	 * 图片组
	 */
	ImageGroup(2, "图片组"),
	/**
	 * 多图组
	 */
	MutliImageGroup(3, "多图组");

	private int value = 0;
	private String desc;
	private TemplateGroupType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TemplateGroupType getEnum(int index) {
		for (TemplateGroupType c : TemplateGroupType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
