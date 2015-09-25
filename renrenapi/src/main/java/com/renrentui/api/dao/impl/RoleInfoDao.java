package com.renrentui.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.api.common.DaoBase;

import com.renrentui.api.dao.inter.IRoleInfoDao;

import com.renrentui.entity.RoleInfo;
@Repository
public class RoleInfoDao extends DaoBase implements IRoleInfoDao{

	@Override
	public int insert(RoleInfo record) {
		return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.IAuthorityRoleDao.insert", record);
	}

	@Override
	public int update(RoleInfo record) {
	    return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.IAuthorityRoleDao.update", record);
	}

	@Override
	public List<RoleInfo> selectList() {
		 return getReadOnlySqlSessionUtil().selectList("com.renrentui.renrenapi.dao.inter.IAuthorityRoleDao.selectList");
	}

}
