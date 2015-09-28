package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenentity.ClienterBalance;



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
