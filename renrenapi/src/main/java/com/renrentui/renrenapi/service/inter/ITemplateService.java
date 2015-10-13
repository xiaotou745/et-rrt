package com.renrentui.renrenapi.service.inter;


import java.util.List;

import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.PageTemplateModel;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.PagedTemplateReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;

public interface ITemplateService {
	public int insert(TemplateModel record) ;
	public int update(TemplateModel record) ;
	public TemplateModel detail(Long templateId) ;
	public  PagedResponse<PageTemplateModel>  queryTemplate(PagedTemplateReq req);
	public List<Template> getAllList(PagedTemplateReq req);
	public int setTemplateStatus(UpdateStatusReq req);
}
