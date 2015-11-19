package com.renrentui.renrenentity.req;

import java.util.ArrayList;

import com.renrentui.renrenentity.domain.SubmitValue;

/**
 * 提交订单接口
 * @author ofmyi_000
 *
 */
public class SubmitTaskReq {
	private Long userId;
	private Long taskId;
	private Long ctId;
	public Long getCtId() {
		return ctId;
	}
	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}
	private ArrayList<SubmitValue> valueInfo;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public ArrayList<SubmitValue> getValueInfo() {
		return valueInfo;
	}
	public void setValueInfo(ArrayList<SubmitValue> valueInfo) {
		this.valueInfo = valueInfo;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
}
