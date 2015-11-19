package com.renrentui.renrenentity.domain;

import java.util.ArrayList;

import com.renrentui.renrenentity.TemplateDetail;

/**
 * 
 * 模板组
 * @author ofmyi_000
 *
 */
public class TemplateGroup {
private Long id;
private Long taskId;//任务ID
private Integer groupType;//组类型 1 文字组 2 图片组
private String title;//组显示标题(前端显示文字)
private ArrayList<TemplateDetail> templateList;//组里面的模板
public ArrayList<TemplateDetail> getTemplateList() {
	return templateList;
}
public void setTemplateList(ArrayList<TemplateDetail> templateList) {
	this.templateList = templateList;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getTaskId() {
	return taskId;
}
public void setTaskId(Long taskId) {
	this.taskId = taskId;
}
public Integer getGroupType() {
	return groupType;
}
public void setGroupType(Integer groupType) {
	this.groupType = groupType;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
}
