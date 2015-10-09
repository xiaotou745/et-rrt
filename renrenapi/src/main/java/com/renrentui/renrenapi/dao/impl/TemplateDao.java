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
	public PagedResponse<PageTemplateModel> queryTemplate(PagedTemplateReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.queryTemplate", req);
	}

	@Override
	public List<Template> getAllList() {
		return getReadOnlySqlSessionUtil().selectList(
				"com.renrentui.renrenapi.dao.inter.ITemplateDao.getAllList");
	}

	@Override
	public int setTemplateStatus(long templateID, int status, String userName) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("templateID", templateID);
		map.put("status", status);
		map.put("userName", userName);
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.ITemplateDao.setTemplateStatus",
						map);
	}

}
