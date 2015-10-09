package com.renrentui.renrencore.enums;

public enum AuditStatus {
	/**
	 * 待审核
	 */
	WaitAudit(0, "待审核"),
	/**
	 * 审核通过
	 */
	Audited(2, "审核通过"),
	/**
	 * 驳回
	 */
	Refuse(3, "审核拒绝");

	private int value = 0;
	private String desc;
	private AuditStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static AuditStatus getEnum(int index) {
		for (AuditStatus c : AuditStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
