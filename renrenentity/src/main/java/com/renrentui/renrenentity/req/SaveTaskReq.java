package com.renrentui.renrenentity.req;

import java.util.ArrayList;

import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemplateGroup;

/*
 * 保存任务
 * 茹化肖2015年11月16日10:38:36
 * */
public class SaveTaskReq {
	public RenRenTask getRenRenTask() {
		return renRenTask;
	}
	public void setRenRenTask(RenRenTask renRenTask) {
		this.renRenTask = renRenTask;
	}
	public ArrayList<TaskSetp> getTaskSetps() {
		return taskSetps;
	}
	public void setTaskSetps(ArrayList<TaskSetp> taskSetps) {
		this.taskSetps = taskSetps;
	}
	public ArrayList<TemplateGroup> getTemplateGroup() {
		return templateGroup;
	}
	public void setTemplateGroup(ArrayList<TemplateGroup> templateGroup) {
		this.templateGroup = templateGroup;
	}
	private RenRenTask renRenTask;//任务信息
	private ArrayList<TaskSetp> taskSetps;//步骤信息 
	private ArrayList<TemplateGroup> templateGroup;
	private String provinceCode;
	private String cityCode;
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}
