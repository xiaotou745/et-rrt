package com.renrentui.renrencore.enums;

public enum ClienterWithdrawFormWithType {
	/**
	 * 支付宝
	 */
	Alipay(1, "支付宝");
	
	private int value = 0;
	private String desc;
	private ClienterWithdrawFormWithType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ClienterWithdrawFormWithType getEnum(int index) {
		for (ClienterWithdrawFormWithType c : ClienterWithdrawFormWithType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
