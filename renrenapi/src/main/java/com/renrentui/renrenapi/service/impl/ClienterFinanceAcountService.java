package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterFinanceAcountDao;
import com.renrentui.renrenapi.service.inter.IClienterFinanceAcountService;
import com.renrentui.renrencore.enums.AccountType;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterFinanceAcount;
import com.renrentui.renrenentity.req.BindAliPayReq;

@Service
public class ClienterFinanceAcountService implements
		IClienterFinanceAcountService {
	@Autowired
	private IClienterFinanceAcountDao clienterFinanceAcountDao;

	@Autowired
	private IClienterDao clienterDao;

	@Override
	public boolean bindAliPay(BindAliPayReq req) {
		Clienter clienter = clienterDao.getClienterByPhoneNo(req.getPhoneNo());
		ClienterFinanceAcount record = new ClienterFinanceAcount();
		record.setClienterid(clienter.getId().intValue());
		record.setTruename(req.getAliName());
		record.setAccountno(req.getAliAccount());
		record.setAccounttype((short) AccountType.AliPay.value());
		record.setCreateby(clienter.getClienterName());
		record.setUpdateby(clienter.getClienterName());
		record.setIsenable(true);
		record.setBelongtype((short) 0);
		ClienterFinanceAcount old=clienterFinanceAcountDao.select(clienter.getId(),AccountType.AliPay.value());
		if (old==null) {
			return clienterFinanceAcountDao.insertSelective(record) > 0;
		}
		record.setId(old.getId());
		return clienterFinanceAcountDao.updateSelective(record) > 0;
	}
}
