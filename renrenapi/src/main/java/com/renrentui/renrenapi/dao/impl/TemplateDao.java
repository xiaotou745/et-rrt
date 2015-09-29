package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateDao;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.PagedTemplateReq;
@Repository
public class TemplateDao extends DaoBase implements ITemplateDao {

	@Override
	public int insert(Template record) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.insert", record);
	}

	@Override
	public TemplateModel detail(int templateId) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.detail", templateId);
	}

	@Override
	public PagedResponse<Template> queryTemplate(PagedTemplateReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.queryTemplate", req);
	}

}
