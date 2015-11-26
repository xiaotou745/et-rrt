package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IRoleInfoDao;
import com.renrentui.renrenentity.RoleInfo;
@Repository
public class RoleInfoDao extends DaoBase implements IRoleInfoDao{

	@Override
	public int insert(RoleInfo record) {
		return getMasterSqlSessionUtil().insert("IRoleInfoDao.insert", record);
	}

	@Override
	public int update(RoleInfo record) {
	    return getMasterSqlSessionUtil().insert("IRoleInfoDao.update", record);
	}

	@Override
	public List<RoleInfo> selectList() {
		 return getReadOnlySqlSessionUtil().selectList("IRoleInfoDao.selectList");
	}

}
