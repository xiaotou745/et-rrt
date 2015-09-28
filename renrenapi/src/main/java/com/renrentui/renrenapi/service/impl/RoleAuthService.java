package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IAccountInfoDao;
import com.renrentui.renrenapi.dao.inter.IRoleAuthDao;
import com.renrentui.renrenapi.service.inter.IRoleAuthService;
import com.renrentui.renrencore.cache.redis.RedisService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.RoleAuth;
import com.renrentui.renrenentity.RoleInfo;

@Service
public class RoleAuthService implements IRoleAuthService{

	@Autowired
	private IRoleAuthDao roleAuthDao;
	@Autowired
	private IAccountInfoDao accountInfoDao;
	@Autowired
	private RedisService redisService;
	@Override
	public boolean modifyAuthList(List<RoleAuth> authList) {
		if (authList!=null&&authList.size()>0) {
			boolean result= roleAuthDao.modifyAuthList(authList);
			//如果这个角色的权限更新成功，则将该角色下的所有用户的权限缓存移除
			if (result) {
				List<AccountInfo> roleAccounts=accountInfoDao.getByRoleID(authList.get(0).getRoleId());
				for (AccountInfo accountInfo : roleAccounts) {
					String key=RedissCacheKey.Menu_Auth+accountInfo.getId();
					redisService.remove(key);
				}
			}
		}
		return false;
	}


}
