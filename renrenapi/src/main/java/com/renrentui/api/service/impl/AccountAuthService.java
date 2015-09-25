package com.renrentui.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.api.dao.inter.IAccountAuthDao;

import com.renrentui.api.service.inter.IAccountAuthService;
import com.renrentui.core.cache.redis.RedisService;
import com.renrentui.core.consts.RedissCacheKey;
import com.renrentui.entity.AccountAuth;


@Service
public class AccountAuthService implements IAccountAuthService{
@Autowired
	private RedisService redisService;
	@Autowired
	private IAccountAuthDao accountAuthDao;
	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return accountAuthDao.getMenuIdsByAccountId(id);
	}
	@Override
	public boolean modifyAuthList(List<AccountAuth> authList) {
		if (authList==null||authList.size()==0) {
			return true;
		}
		boolean result=accountAuthDao.modifyAuthList(authList);
		if (result) {
			String key=RedissCacheKey.Menu_Auth+authList.get(0).getAccoutid();
			redisService.remove(key);
		}
		return result;
	}
}
