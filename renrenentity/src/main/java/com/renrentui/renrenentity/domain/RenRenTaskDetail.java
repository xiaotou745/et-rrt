package com.renrentui.renrenentity.domain;

import java.util.List;

import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.TaskCityRelation;

public class RenRenTaskDetail{
private RenRenTask taskInfo;
private List<TaskCityRelation> cityRelationList;
private List<Attachment> attachmentsList;
public List<TaskCityRelation> getCityRelationList() {
	return cityRelationList;
}
public void setCityRelationList(List<TaskCityRelation> cityRelationList) {
	this.cityRelationList = cityRelationList;
}
public List<Attachment> getAttachmentsList() {
	return attachmentsList;
}
public void setAttachmentsList(List<Attachment> attachmentsList) {
	this.attachmentsList = attachmentsList;
}
public RenRenTask getTaskInfo() {
	return taskInfo;
}
public void setTaskInfo(RenRenTask taskInfo) {
	this.taskInfo = taskInfo;
}
}
