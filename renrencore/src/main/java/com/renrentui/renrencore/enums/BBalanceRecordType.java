package com.renrentui.renrencore.enums;

public enum BBalanceRecordType {
	/**
	 * 发布任务
	 */
	ReleaseTask(1, "发布任务"),
	/**
	 * 充值
	 */
	Delta(2, "充值"),
	/**
	 * 取消任务(修改任务时，切换了商家，则任务原来的商家要取消任务)
	 */
	CancelTask(3, "取消任务"),
	/**
	 * 驳回任务
	 */
	RejectTask(4, "驳回任务"),
	/**
	 * 修改任务
	 */
	UpdateTask(5, "修改任务"),
	/**
	 * 任务结算
	 */
	TaskSettlement (6, "任务结算");
	
	private int value = 0;
	private String desc;
	private BBalanceRecordType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static BBalanceRecordType getEnum(int index) {
		for (BBalanceRecordType c : BBalanceRecordType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
