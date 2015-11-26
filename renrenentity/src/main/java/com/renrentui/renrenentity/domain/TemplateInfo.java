package com.renrentui.renrenentity.domain;

import java.util.ArrayList;
import java.util.List;

import com.renrentui.renrenentity.RenRenTask;

public class TemplateInfo {
	private RenRenTask task;
	private List<TaskDatumDetailGroup> templateGroup;
	public RenRenTask getTask() {
		return task;
	}
	public void setTask(RenRenTask task) {
		this.task = task;
	}
	public List<TaskDatumDetailGroup> getTemplateGroup() {
		return templateGroup;
	}
	public void setTemplateGroup(List<TaskDatumDetailGroup> templateGroup) {
		this.templateGroup = templateGroup;
	}
}
