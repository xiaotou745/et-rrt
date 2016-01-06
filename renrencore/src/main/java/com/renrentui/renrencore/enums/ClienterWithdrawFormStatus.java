package com.renrentui.renrencore.enums;

public enum ClienterWithdrawFormStatus {
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
	AuditRefuse(2, "审核拒绝"),
	
	Paying(20,"打款中"),
	
	PaySuccess(3,"打款完成"),
	
	PayError(30,"打款失败");

	private int value = 0;
	private String desc;
	private ClienterWithdrawFormStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ClienterWithdrawFormStatus getEnum(int index) {
		for (ClienterWithdrawFormStatus c : ClienterWithdrawFormStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
