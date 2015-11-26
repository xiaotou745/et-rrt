package com.renrentui.renrencore.enums;

public enum TaskCode {
	/**
	 * 成功
	 */
	Success(200, "success"),
	GetTaskFail(1101, "获取任务失败"),
	UserIdErr(1102, "用户ID错误"),
	OrderType(1103, "任务类型不能为空"),  
	CityCode(1104, "城市编码错误"),  
	TaskStatus(1105, "任务状态只能为进行中(1)或已过期(3)"),  
	MsgId(1106, "消息id不存在"), 
	MsgOpType(1107, "消息的操作类型只能为删除(1)或已读(2)"), 
	DatumAuditStatus(1108, "资料审核状态只能为待审核(1)或审核通过(2)或审核拒绝(3)"),  
	TaskId(1109, "任务id不能<=0"),  
	Fail(1146, "系统错误");
	private int value = 0;
	private String desc;
	private TaskCode(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static GetTaskCode getEnum(int index) {
		for (GetTaskCode c : GetTaskCode.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
