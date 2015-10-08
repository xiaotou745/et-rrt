package com.renrentui.renrenapi.service.inter;


import java.util.List;

import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.PagedTemplateReq;

public interface ITemplateService {
	public int insert(TemplateModel record) ;
	public TemplateModel detail(int templateId) ;
	public  PagedResponse<Template>  queryTemplate(PagedTemplateReq req);
	public List<Template> getAllList();
}
