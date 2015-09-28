package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IRoleAuthDao;
import com.renrentui.renrenentity.RoleAuth;
import com.renrentui.renrenentity.RoleInfo;

@Repository
public class RoleAuthDao extends DaoBase implements
		IRoleAuthDao {

	@Override
	public boolean modifyAuthList(List<RoleAuth> authList) {
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.IRoleAuthDao.modifyAuthList",
						authList) > 0;
	}




}
