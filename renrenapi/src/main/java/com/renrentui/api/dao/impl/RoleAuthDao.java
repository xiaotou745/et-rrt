package com.renrentui.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.api.common.DaoBase;
import com.renrentui.api.dao.inter.IRoleAuthDao;
import com.renrentui.entity.RoleAuth;
import com.renrentui.entity.RoleInfo;

@Repository
public class RoleAuthDao extends DaoBase implements
		IRoleAuthDao {

	@Override
	public boolean modifyAuthList(List<RoleAuth> authList) {
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.IAuthorityRoleMentMenuSetDao.modifyAuthList",
						authList) > 0;
	}




}
