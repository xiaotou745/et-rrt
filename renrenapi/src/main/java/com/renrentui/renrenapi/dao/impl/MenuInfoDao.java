package com.renrentui.renrenapi.dao.impl;


import java.util.List;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IMenuInfoDao;
import com.renrentui.renrencore.cache.redis.RedisService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrenentity.MenuInfo;
import com.renrentui.renrenentity.domain.MenuEntity;


@Repository
public class MenuInfoDao extends DaoBase implements
		IMenuInfoDao {
	@Autowired
	private RedisService redisService;


	@Override
	public List<MenuInfo> getMenuListByUserID(int accountId) {
		String key=RedissCacheKey.Menu_Auth+accountId;
		List<MenuInfo> result=redisService.get(key, List.class);
		result=null;
		if (result==null||result.size()==0) {
			List<MenuInfo> list = getReadOnlySqlSessionUtil()
					.selectList(
							"com.renrentui.renrenapi.dao.inter.IMenuInfoDao.getMenuListByUserID",
							accountId);
			redisService.set(key, list);
			return list;
		}

		return result;
	}

	@Override
	public List<MenuEntity> getAuthSettingList(int userID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.renrentui.renrenapi.dao.inter.IMenuInfoDao.getAuthSettingList",
						userID);
	}

	@Override
	public List<MenuEntity> getMenuListByRoleID(int roleID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.renrentui.renrenapi.dao.inter.IMenuInfoDao.getMenuListByRoleID",
						roleID);
	}

	@Override
	public List<MenuInfo> getListMenuByParId(int parId) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.renrentui.renrenapi.dao.inter.IMenuInfoDao.getListMenuByParId",
						parId);
	}

	@Override
	public MenuInfo getMenuById(int id) {
		return getReadOnlySqlSessionUtil()
				.selectOne(
						"com.renrentui.renrenapi.dao.inter.IMenuInfoDao.getMenuById",
						id);
	}

	@Override
	public boolean addMenu(MenuInfo req) {
		return getMasterSqlSessionUtil()
				.insert(
						"com.renrentui.renrenapi.dao.inter.IMenuInfoDao.addMenu",
						req) > 0;
	}
}
