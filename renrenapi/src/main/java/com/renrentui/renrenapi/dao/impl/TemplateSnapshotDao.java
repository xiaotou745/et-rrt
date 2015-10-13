package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.TemplateSnapshot;
import com.renrentui.renrenentity.req.TemplateSnapshotReq;
@Repository
public class TemplateSnapshotDao extends DaoBase implements ITemplateSnapshotDao{

	@Override
	public int copySnapshot(TemplateSnapshotReq req) {
		return getMasterSqlSessionUtil()
				.insert("com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao.copySnapshot",
						req);
	}

	@Override
	public int deleteById(Long id) {
		return  getReadOnlySqlSessionUtil().delete(
				"com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao.deleteById", id);
	}
	@Override
	public int deleteByTemplateId(Long templateId) {
		return  getReadOnlySqlSessionUtil().delete(
				"com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao.deleteByTemplateId", templateId);

	}
	@Override
	public TemplateSnapshot detailById(Long id) {
		return  getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao.detailById", id);
	}
	@Override
	public TemplateSnapshot detailByTemplateId(Long templateId) {
		return  getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao.detailByTemplateId", templateId);
	}
}
