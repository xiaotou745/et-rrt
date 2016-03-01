package com.renrentui.renrenentity.req;

import java.util.List;

import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.TaskCityRelation;
import com.renrentui.renrenentity.domain.TaskRegion;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemplateGroup;

/*
 * 保存任务
 * 茹化肖2015年11月16日10:38:36
 * */
public class SaveTaskReq {
	private RenRenTask renRenTask;//任务信息
	private List<TaskSetp> taskSetps;//步骤信息 
	private List<TemplateGroup> templateGroup;
	private List<TaskCityRelation> taskRegions;//任务投放区域信息
	private String beginTime;
	private String endTime;
	public RenRenTask getRenRenTask() {
		return renRenTask;
	}
	public void setRenRenTask(RenRenTask renRenTask) {
		this.renRenTask = renRenTask;
	}
	public List<TaskSetp> getTaskSetps() {
		return taskSetps;
	}
	public void setTaskSetps(List<TaskSetp> taskSetps) {
		this.taskSetps = taskSetps;
	}
	public List<TemplateGroup> getTemplateGroup() {
		return templateGroup;
	}
	public void setTemplateGroup(List<TemplateGroup> templateGroup) {
		this.templateGroup = templateGroup;
	}

	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public List<TaskCityRelation> getTaskRegions() {
		return taskRegions;
	}
	public void setTaskRegions(List<TaskCityRelation> taskRegions) {
		this.taskRegions = taskRegions;
	}
}
