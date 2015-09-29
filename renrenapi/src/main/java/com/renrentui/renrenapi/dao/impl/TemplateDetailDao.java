package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenentity.TemplateDetail;
@Repository
public class TemplateDetailDao extends DaoBase implements ITemplateDetailDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getMasterSqlSessionUtil().delete(
				"com.renrentui.renrenapi.dao.inter.ITemplateDetailDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(TemplateDetail record) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.ITemplateDetailDao.insert", record);
	}

	@Override
	public int insertList(List<TemplateDetail> recordList) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.ITemplateDetailDao.insertList", recordList);
	}

}
