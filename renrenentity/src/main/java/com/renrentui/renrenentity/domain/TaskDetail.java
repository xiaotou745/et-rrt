package com.renrentui.renrenentity.domain;

import java.util.ArrayList;

import com.renrentui.renrencore.enums.PaymentMethodType;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.RenRenTask;

/**
 * 任务详情实体 
 * @author ofmyi_000
 *
 */
public class TaskDetail {
	private RenRenTask task;
	private ArrayList<TaskSetp> taskSetps;
	public RenRenTask getTask() {
		return task;
	}
	public void setTask(RenRenTask task) {
		this.task = task;
	}
	public ArrayList<TaskSetp> getTaskSetps() {
		return taskSetps;
	}
	public void setTaskSetps(ArrayList<TaskSetp> taskSetps) {
		this.taskSetps = taskSetps;
	}
	
}
