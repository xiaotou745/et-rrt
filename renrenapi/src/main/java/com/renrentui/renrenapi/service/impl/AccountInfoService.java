package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IAccountInfoDao;
import com.renrentui.renrenapi.service.inter.IAccountInfoService;
import com.renrentui.renrencore.cache.redis.RedisService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.security.MD5Util;
import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedAccountInfoReq;


@Service
public class AccountInfoService implements IAccountInfoService{

	@Autowired
	private IAccountInfoDao accountInfoDao;
	@Autowired
	private RedisService redisService;
	@Override
	public PagedResponse<AccountInfo> queryAccount(PagedAccountInfoReq req) {
		return  accountInfoDao.queryAccount(req);
	}

	@Override
	public AccountInfo login(String username, String password) {
		password = MD5Util.MD5(password);
		return accountInfoDao.login(username, password);
	}

	@Override
	public AccountInfo getByID(int userID) {
		return accountInfoDao.getByID(userID);
	}

	@Override
	public int updateRoleID(int userID, int newRoleID) {
		int result= accountInfoDao.updateRoleID(userID,newRoleID);
		if (result>0) {
			String key=RedissCacheKey.Menu_Auth+userID;
			redisService.remove(key);
		}
		return result;
	}

	@Override
	public List<AccountInfo> getByRoleID(int roleID) {
		return accountInfoDao.getByRoleID(roleID);
	}
}
