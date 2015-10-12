package com.renrentui.renrencore.enums;

public enum UploadForm {
	
	Task(1,"发布任务"),
	Business(2, "商家"),
	Clienter(3,"递推员");
	
	private int value = 0;
	private String desc;
	private UploadForm(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static UploadForm getEnum(int index) {
		for (UploadForm c : UploadForm.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
