package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterRelationDao;
import com.renrentui.renrenentity.ClienterRelation;
import com.renrentui.renrenentity.domain.ClienterRelationLevelModel;
import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.domain.PartnerItem;
import com.renrentui.renrenentity.req.CRelationReq;
import com.renrentui.renrenentity.req.PagedPartnerListReq;
@Repository
public class ClienterRelationDao extends DaoBase implements IClienterRelationDao {
	/**
	 * 通过骑士ID查询自己的上级关系
	 * (事务中 写串)
	 */
	@Override
	public List<ClienterRelation> getRelastionListByClienterId(Long clienterId,int flag) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("clienterId", clienterId);
		map.put("flag", flag);
		return getMasterSqlSessionUtil().selectList("IClienterRelationDao.getRelastionListByClienterId", map);
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
	@Override	
	public List<PartnerItem> getPagedPartnerListByUserId(PagedPartnerListReq req) {
		return getReadOnlySqlSessionUtil().selectList("IClienterRelationDao.getPagedPartnerListByUserId", req);
	}

}
