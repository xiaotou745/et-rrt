package com.renrentui.renrenentity.domain;

public class Article {
private Integer id;
private String title;
private String content;
private String createName;
private String updateName;
private String createDate;
public String getCreateDate() {
	return createDate;
}
public void setCreateDate(String createDate) {
	this.createDate = createDate;
}
public String getCreateName() {
	return createName;
}
public void setCreateName(String createName) {
	this.createName = createName;
}
public String getUpdateName() {
	return updateName;
}
public void setUpdateName(String updateName) {
	this.updateName = updateName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
}
