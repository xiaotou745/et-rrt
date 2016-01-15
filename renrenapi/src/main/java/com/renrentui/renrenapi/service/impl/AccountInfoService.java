package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IAccountInfoDao;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IAccountInfoService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.security.MD5Util;
import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedAccountInfoReq;
import com.renrentui.renrenentity.req.UpdatePwdReq;


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

	@Override
	public int insert(AccountInfo account) {
		return accountInfoDao.insert(account);
	}

	@Override
	public int update(AccountInfo account) {
		return accountInfoDao.update(account);
	}

	@Override
	public int updatePwd(UpdatePwdReq req) {
		String oldPassword = MD5Util.MD5(req.getOldPwd());
		String newPassword = MD5Util.MD5(req.getNewPwd());
		req.setOldPwd(oldPassword);
		req.setNewPwd(newPassword);
		return accountInfoDao.updatePwd(req);
	}
}
