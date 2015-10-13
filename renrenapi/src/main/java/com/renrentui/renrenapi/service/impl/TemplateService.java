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
import com.renrentui.renrenentity.domain.PageTemplateModel;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.PagedTemplateReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;

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
	public TemplateModel detail(Long templateId) {
		TemplateModel result= new TemplateModel();
		Template model=templateDao.detail(templateId);
		List<TemplateDetail> detailList=templateDetailDao.listByTemplateId(templateId);
		result.setId(model.getId());
		result.setTemplateName(model.getTemplateName());
		result.setRemark(model.getRemark());
		result.setStatus(model.getStatus());
		result.setBusinessId(model.getBusinessId());
		result.setCreateName(model.getCreateName());
		result.setCreateTime(model.getCreateTime());
		result.setLastOptName(model.getLastOptName());
		result.setLastOptTime(model.getLastOptTime());
		result.setDetailList(detailList);
		return result;
	}

	@Override
	public PagedResponse<PageTemplateModel> queryTemplate(PagedTemplateReq req) {
		return templateDao.queryTemplate(req);
	}

	@Override
	public List<Template> getAllList(PagedTemplateReq req) {
		return templateDao.getAllList(req);
	}

	@Override
	public int setTemplateStatus(UpdateStatusReq req) {
		return templateDao.setTemplateStatus(req);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int update(TemplateModel record) {
		 int result=templateDao.update(record);
		 if (result>0) {
			 templateDetailDao.deleteByTemplateId(record.getId());
			 templateDetailDao.insertList(record.getDetailList());
		 }
		 return result;
	}

}
