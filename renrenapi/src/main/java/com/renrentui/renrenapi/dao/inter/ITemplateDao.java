package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.PageTemplateModel;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.PagedTemplateReq;

public interface ITemplateDao {
	public int insert(Template record) ;
	public TemplateModel detail(int templateId) ;
	public  PagedResponse<PageTemplateModel>  queryTemplate(PagedTemplateReq req);
	public List<Template> getAllList();
	public int setTemplateStatus(long templateID,int status,String userName);
}