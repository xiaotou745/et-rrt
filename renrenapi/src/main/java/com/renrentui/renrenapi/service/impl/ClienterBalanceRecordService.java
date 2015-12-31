package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.service.inter.IClienterBalanceRecordService;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ClienterBlanceRecordReq;

@Service
public class ClienterBalanceRecordService implements IClienterBalanceRecordService{
@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;
	@Override
	public List<ClienterBalanceRecord> getRecordList(Long clienterId) {
return clienterBalanceRecordDao.getRecordList(clienterId);
	}
	/**
	 * 地推余额流水分页
	 */
	@Override
	public PagedResponse<ClienterBalanceRecord> getRecordList(
			ClienterBlanceRecordReq req) {
		return clienterBalanceRecordDao.getRecordList(req);
	}

}
