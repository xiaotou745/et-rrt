package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IAccountAuthDao;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IAccountAuthService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrenentity.AccountAuth;


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
			String key=RedissCacheKey.Menu_Auth+authList.get(0).getAccoutId();
			redisService.remove(key);
		}
		return result;
	}
}
