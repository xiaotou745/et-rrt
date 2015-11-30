package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.TemplateSnapshot;
import com.renrentui.renrenentity.req.TemplateSnapshotReq;

/**
 * 模板快照
 * V1.0.2需求删除 茹化肖
 * @author ofmyi_000
 *
 */
@Repository
public class TemplateSnapshotDao extends DaoBase implements ITemplateSnapshotDao{

//	@Override
//	public int copySnapshot(TemplateSnapshotReq req) {
//		return getMasterSqlSessionUtil()
//				.insert("ITemplateSnapshotDao.copySnapshot",
//						req);
//	}
//
//	@Override
//	public int deleteById(Long id) {
//		return  getReadOnlySqlSessionUtil().delete(
//				"ITemplateSnapshotDao.deleteById", id);
//	}
//	@Override
//	public int deleteByTemplateId(Long templateId) {
//		return  getReadOnlySqlSessionUtil().delete(
//				"ITemplateSnapshotDao.deleteByTemplateId", templateId);
//
//	}
//	@Override
//	public TemplateSnapshot detailById(Long id) {
//		return  getReadOnlySqlSessionUtil().selectOne(
//				"ITemplateSnapshotDao.detailById", id);
//	}
//	@Override
//	public TemplateSnapshot detailByTemplateId(Long templateId) {
//		return  getReadOnlySqlSessionUtil().selectOne(
//				"ITemplateSnapshotDao.detailByTemplateId", templateId);
//	}
}
