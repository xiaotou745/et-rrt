package com.renrentui.renrencore.enums;

public enum ClienterStatus {
	/**
	 * 待审核
	 */
	UnAudit(0, "待审核"),
	/**
	 * 审核通过
	 */
	Audited(1, "审核通过"),
	/**
	 * 审核拒绝
	 */
	AuditRefuse(2, "审核拒绝");

	private int value = 0;
	private String desc;
	private ClienterStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ClienterStatus getEnum(int index) {
		for (ClienterStatus c : ClienterStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
