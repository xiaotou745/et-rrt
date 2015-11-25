package com.renrentui.renrencore.enums;

public enum MsgOpType {
	/**
	 * 新增
	 */
	Insert(0, "新增"),
	/**
	 * 删除
	 */
	DeleteMsg(1, "删除"),
	/**
	 * 已读
	 */
	ReadMsg(2, "已读");
	private int value = 0;
	private String desc;
	private MsgOpType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static MsgOpType getEnum(int index) {
		for (MsgOpType c : MsgOpType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
