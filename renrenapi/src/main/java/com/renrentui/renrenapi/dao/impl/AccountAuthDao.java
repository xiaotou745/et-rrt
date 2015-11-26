package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IAccountAuthDao;
import com.renrentui.renrenentity.AccountAuth;


/**
 * 用户菜单设置
 * @author pengyi
 * @date 20150828
 *
 */
@Repository
public class AccountAuthDao extends DaoBase implements IAccountAuthDao{

	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return getReadOnlySqlSessionUtil().selectList(
				"IAccountAuthDao.getMenuIdsByAccountId",id);
	}

	@Override
	public boolean modifyAuthList(List<AccountAuth> authList) {
		return getMasterSqlSessionUtil()
				.update("IAccountAuthDao.modifyAuthList",
						authList) > 0;

	}

}
