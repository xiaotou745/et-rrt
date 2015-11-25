package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class PagedArticleReq  extends PagedRequestBase {
	private Integer id;
	private String title;
	private Integer type;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
}
