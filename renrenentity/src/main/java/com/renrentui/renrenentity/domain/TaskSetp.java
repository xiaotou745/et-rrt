package com.renrentui.renrenentity.domain;
/**
 * 任务步骤表实体
 * @author ofmyi_000
 *
 */
public class TaskSetp {
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
	public String getLinkTitle() {
		return linkTitle;
	}
	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Integer getSetpType() {
		return setpType;
	}
	public void setSetpType(Integer setpType) {
		this.setpType = setpType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
private Long id;
private Long taskId;//任务ID
private String linkTitle;//链接文本(SetpType=3才有用)
private Integer sortNo ;//排序编号
private Integer setpType;//类型 1 步骤 2 补充说明 3 细则(URL链接)
private String content;//具体内容 setpType为 1 或者2 时,是文本内容 为3 时  是链接地址Url
}
