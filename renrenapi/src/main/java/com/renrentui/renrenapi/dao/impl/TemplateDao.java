package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateDao;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.PageTemplateModel;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.PagedTemplateReq;
import com.renrentui.renrenentity.req.TemplateSnapshotReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;
@Repository
public class TemplateDao extends DaoBase implements ITemplateDao {
	@Override
	public int insert(Template record) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.insert", record);
	}

	@Override
	public Template detail(Long templateId) {
		return  getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.detail", templateId);
	}

	@Override
	public PagedResponse<PageTemplateModel> queryTemplate(PagedTemplateReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.queryTemplate", req);
	}

	@Override
	public List<Template> getAllList(PagedTemplateReq req) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.getAllList",req);
	}

	@Override
	public int setTemplateStatus(UpdateStatusReq req) {
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.ITemplateDao.setTemplateStatus",
						req);
	}

	@Override
	public int update(Template record) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.update", record);
	}



}
