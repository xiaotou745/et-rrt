package com.renrentui.renrencore.enums;

public enum RegionCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	
	NewVersion(201,"请获取最新版区域信息"),
	Version(202,"版本号不能为空"),
	/**
	 * 
	 */
	Fail(1024, "系统错误");
	private int value = 0;
	private String desc;
	private RegionCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static RegionCode getEnum(int index) {
		for (RegionCode c : RegionCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}

}
