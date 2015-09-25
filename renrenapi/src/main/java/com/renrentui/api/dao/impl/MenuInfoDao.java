package com.renrentui.api.dao.impl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renrentui.api.common.DaoBase;

import com.renrentui.api.dao.inter.IMenuInfoDao;
import com.renrentui.core.cache.redis.RedisService;
import com.renrentui.core.consts.RedissCacheKey;

import com.renrentui.entity.MenuInfo;


@Repository
public class MenuInfoDao extends DaoBase implements
		IMenuInfoDao {
	@Autowired
	private RedisService redisService;


	@Override
	public List<MenuInfo> getMenuListByUserID(int accountId) {
		String key=RedissCacheKey.Menu_Auth+accountId;
		List<MenuInfo> result=redisService.get(key, List.class);
		if (result==null||result.size()==0) {
			List<MenuInfo> list = getReadOnlySqlSessionUtil()
					.selectList(
							"com.renrentui.renrenapi.dao.inter.IAuthorityMenuClassDao.getMenuListByUserID",
							accountId);
			redisService.set(key, list);
			return list;
		}

		return result;
	}

	@Override
	public List<MenuInfo> getAuthSettingList(int userID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.renrentui.renrenapi.dao.inter.IAuthorityMenuClassDao.getAuthSettingList",
						userID);
	}

	@Override
	public List<MenuInfo> getRoleAuthSettingList(int roleID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.renrentui.renrenapi.dao.inter.IAuthorityMenuClassDao.getRoleAuthSettingList",
						roleID);
	}

	@Override
	public List<MenuInfo> getListMenuByParId(int parId) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.renrentui.renrenapi.dao.inter.IAuthorityMenuClassDao.getListMenuByParId",
						parId);
	}

	@Override
	public MenuInfo getMenuById(int id) {
		return getReadOnlySqlSessionUtil()
				.selectOne(
						"com.renrentui.renrenapi.dao.inter.IAuthorityMenuClassDao.getMenuById",
						id);
	}

	@Override
	public boolean addMenu(MenuInfo req) {
		return getMasterSqlSessionUtil()
				.insert(
						"com.renrentui.renrenapi.dao.inter.IAuthorityMenuClassDao.addMenu",
						req) > 0;
	}

	@Override
	public boolean checkHasAuth(int userID, int menuID) {
		// TODO Auto-generated method stub
		return false;
	}


}
