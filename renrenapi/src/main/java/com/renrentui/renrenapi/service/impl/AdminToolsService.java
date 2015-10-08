package com.renrentui.renrenapi.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.IAdminToolsService;
import com.renrentui.renrencore.cache.redis.RedisService;

@Service
public class AdminToolsService implements IAdminToolsService {

	@Autowired
	RedisService redisService = null;

	/**
	 * 获取redis工具
	 * 
	 * @author haichao
	 * @date 2015年9月29日 11:56:34
	 * @param key
	 *            键 ,sType类型，1 模糊查询 key 2获取指定key的值
	 * **/
	@Override
	public Set<String> getReidsTools(String key, int sType) {
		if (sType == 1) {
			// keys
			return redisService.keys(key);
		}
		Set<String> set = new HashSet<String>();
		if (sType == 2) {
			// 查询指定值
			set.add(redisService.get(key, String.class));
			return set;
		}
		set.add("");
		return set;
	}

}