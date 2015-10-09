package com.renrentui.renrencore.enums;

public enum OrderStatus {
	/**
	 * 进行中
	 */
	Gonging(0, "进行中"),
	/**
	 * 已完成
	 */
	Complete(1, "已完成"),
	/**
	 * 已取消
	 */
	Cancel(2, "已取消"),
	/**
	 * 超时取消
	 */
	OverTimeCancel(3, "超时取消");
	private int value = 0;
	private String desc;
	private OrderStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static OrderStatus getEnum(int index) {
		for (OrderStatus c : OrderStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
