package com.renrentui.api.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.renrentui.api.common.DaoBase;
import com.renrentui.api.dao.inter.IAccountAuthDao;
import com.renrentui.entity.AccountAuth;


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
				"com.renrentui.renrenapi.dao.inter.IAuthorityAccountMenuSetDao.getMenuIdsByAccountId",id);
	}

	@Override
	public boolean modifyAuthList(List<AccountAuth> authList) {
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.IAuthorityAccountMenuSetDao.modifyAuthList",
						authList) > 0;

	}

}
