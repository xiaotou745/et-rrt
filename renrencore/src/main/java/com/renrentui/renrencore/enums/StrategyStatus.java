package com.renrentui.renrencore.enums;

public enum StrategyStatus {
	/**
	 * 待审核
	 */
	UnEnable(1, "禁用"),
	/**
	 * 审核通过
	 */
	Enable(2, "启用"),
	/**
	 * 驳回
	 */
	Delete(3, "删除");

	private int value = 0;
	private String desc;
	private StrategyStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static StrategyStatus getEnum(int index) {
		for (StrategyStatus c : StrategyStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
