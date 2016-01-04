package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterRelationDao;
import com.renrentui.renrenentity.ClienterRelation;
import com.renrentui.renrenentity.domain.ClienterRelationLevelModel;
import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.req.CRelationReq;
@Repository
public class ClienterRelationDao extends DaoBase implements IClienterRelationDao {
	/**
	 * 通过骑士ID查询自己的上级关系
	 * (事务中 写串)
	 */
	@Override
	public List<ClienterRelation> getRelastionListByClienterId(Long clienterId) {
		return getMasterSqlSessionUtil().selectList("IClienterRelationDao.getRelastionListByClienterId", clienterId);
	}
	/**
	 * 插入一个层级关系
	 */
	@Override
	public int insertClienterRelation(ClienterRelation model) {
		return getMasterSqlSessionUtil().insert("IClienterRelationDao.insertClienterRelation", model);
	}
	/**
	 * 根据骑士ID查询自己的等级
	 */
	@Override
	public int getLevelByClienterId(Long clienterId) {
		return getMasterSqlSessionUtil().selectOne("IClienterRelationDao.getLevelByClienterId", clienterId);
	}
	/**
	 * 通过手机号查询
	 */
	@Override
	public List<ClienterRelationModel> getClienterRelationModelsByPhone(
			CRelationReq req) {
		return getReadOnlySqlSessionUtil().selectList("IClienterRelationDao.getClienterRelationModelsByPhone", req);
	}
	@Override
	public List<ClienterRelationLevelModel> getClienterRelationModelsByJibie(
			CRelationReq req) {
		return getReadOnlySqlSessionUtil().selectList("IClienterRelationDao.getClienterRelationModelsByJibie", req);
	}

}
