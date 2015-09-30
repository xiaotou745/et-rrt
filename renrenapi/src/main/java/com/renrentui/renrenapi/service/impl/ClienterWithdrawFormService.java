package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;



@Service
public class ClienterWithdrawFormService implements IClienterWithdrawFormService{

	@Autowired
	private IClienterWithdrawFormDao clienterWithdrawFormDao;	
	

	@Override
	public int Add(ClienterWithdrawForm record) 
	{
		return clienterWithdrawFormDao.insert(record);
	}
	
	@Override
	public	PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req)
	{
		return clienterWithdrawFormDao.getList(req);		
	}
}
