package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.service.inter.IClienterBalanceRecordService;
import com.renrentui.renrenentity.ClienterBalanceRecord;

@Service
public class ClienterBalanceRecordService implements IClienterBalanceRecordService{
@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;
	@Override
	public List<ClienterBalanceRecord> getRecordList(Long clienterId) {
return clienterBalanceRecordDao.getRecordList(clienterId);
	}

}
