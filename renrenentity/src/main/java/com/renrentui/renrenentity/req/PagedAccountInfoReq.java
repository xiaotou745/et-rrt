package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;


public class PagedAccountInfoReq extends PagedRequestBase{

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
	   this.keyword = keyword;
	}
	private int Id;
	private String keyword;
}