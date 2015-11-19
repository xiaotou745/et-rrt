package com.renrentui.renrenentity.domain;

import java.util.ArrayList;
import java.util.List;

import com.renrentui.renrenentity.RenRenTask;

public class TemplateInfo {
	private RenRenTask task;
	private List<TemplateGroup> templateGroup;
	public RenRenTask getTask() {
		return task;
	}
	public void setTask(RenRenTask task) {
		this.task = task;
	}
	public List<TemplateGroup> getTemplateGroup() {
		return templateGroup;
	}
	public void setTemplateGroup(List<TemplateGroup> templateGroup) {
		this.templateGroup = templateGroup;
	}
}
