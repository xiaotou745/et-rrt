package com.renrentui.renrenapi.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.ITemplateDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenapi.service.inter.ITemplateService;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.TemplateDetail;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.PagedTemplateReq;

@Service
public class TemplateService implements ITemplateService {
	@Autowired
	private ITemplateDao templateDao;
	@Autowired
	private ITemplateDetailDao templateDetailDao;
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int insert(TemplateModel record) {
		int result= templateDao.insert(record);
		if (result>0) {
			for (TemplateDetail detail : record.getDetailList()) {
				detail.setTemplateId(record.getId());
			}
			return templateDetailDao.insertList(record.getDetailList());
		}
		return result;
	}

	@Override
	public TemplateModel detail(int templateId) {
		return templateDao.detail(templateId);
	}

	@Override
	public PagedResponse<Template> queryTemplate(PagedTemplateReq req) {
		return templateDao.queryTemplate(req);
	}

	@Override
	public List<Template> getAllList() {
		return templateDao.getAllList();
	}

}
