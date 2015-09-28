package com.renrentui.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.api.dao.inter.IAccountAuthDao;
import com.renrentui.api.dao.inter.IClienterBalanceDao;
import com.renrentui.api.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.api.service.inter.IAccountAuthService;
import com.renrentui.api.service.inter.IClienterBalanceService;
import com.renrentui.api.service.inter.IClienterWithdrawFormService;
import com.renrentui.core.cache.redis.RedisService;
import com.renrentui.core.consts.RedissCacheKey;
import com.renrentui.entity.AccountAuth;
import com.renrentui.entity.BusinessBalance;
import com.renrentui.entity.ClienterBalance;
import com.renrentui.entity.ClienterWithdrawForm;


@Service
public class ClienterBalanceService implements IClienterBalanceService{

	@Autowired
	private IClienterBalanceDao clienterBalanceDao;
	
	

	@Override
	public  ClienterBalance selectByPrimaryKey(Long id)
	{
		ClienterBalance model=clienterBalanceDao.selectByPrimaryKey(id);		

		return model;	
	}
}
