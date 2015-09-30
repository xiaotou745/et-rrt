package com.renrentui.renrenentity.domain;

import java.util.List;

import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.TemplateDetail;

public class TemplateModel extends Template{
	private List<TemplateDetail> detailList;

	public List<TemplateDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<TemplateDetail> detailList) {
		this.detailList = detailList;
	}
}
