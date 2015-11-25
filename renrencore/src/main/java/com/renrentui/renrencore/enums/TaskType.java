package com.renrentui.renrencore.enums;

/**
 * 任务类型：1 签约任务 2 分享任务 3 下载任务
 * @author hailongzhao
 * @date 20151124
 */
public enum TaskType {
	/**
	 * 签约任务
	 */
	ContractTask(1, "签约任务"),
	/**
	 * 分享任务
	 */
	ShareTask(2, "分享任务"),
	/**
	 * 下载任务
	 */
	DownLoadTask(3, "下载任务");

	private int value = 0;
	private String desc;
	private TaskType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static TaskType getEnum(int index) {
		for (TaskType c : TaskType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
