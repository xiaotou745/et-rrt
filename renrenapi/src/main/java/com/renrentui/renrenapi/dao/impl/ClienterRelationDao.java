package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterRelationDao;
import com.renrentui.renrenentity.ClienterRelation;
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

}
