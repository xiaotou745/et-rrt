package com.renrentui.renrenapihttp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.ITaskService;
import com.renrentui.renrencore.cache.redis.RedisService;

/**
 * 任务相关
 * 
 * @author 茹化肖
 * @date 2015年9月28日09:59:42
 */
@Service
public class TaskService implements ITaskService{
	/**
	 * C端任务详情接口
	 */
	@Override
	public HttpResultModel<Object> taskDeatil() {
		// TODO Auto-generated method stub
		return null;
	}
//	@Autowired
//	IClienterService clienterService;
//	
//	@Autowired
//	private IClienterBalanceService clienterBalanceService;	
//
//	@Autowired
//	RedisService redisService;
}
