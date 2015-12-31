package com.renrentui.renrenapi.dao.impl;


import java.util.List;



import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ClienterBlanceRecordReq;


@Repository
public class ClienterBalanceRecordDao extends DaoBase implements IClienterBalanceRecordDao {

	@Override
	public int insert(ClienterBalanceRecord record) {
		return getMasterSqlSessionUtil().insert(
				"IClienterBalanceRecordDao.insert", record);
	}
	
	@Override
	public ClienterBalanceRecord selectByPrimaryKey(Long id) {	
		return getMasterSqlSessionUtil().selectOne(
				"IClienterBalanceRecordDao.selectByPrimaryKey", id);
	}
	
	@Override
	public ClienterBalanceRecord selectByOrderId(Long orderId) {	
		return getMasterSqlSessionUtil().selectOne(
				"IClienterBalanceRecordDao.selectByOrderId", orderId);
	}
	
	@Override
	public int updateByPrimaryKeySelective(ClienterBalanceRecord record) {
		return getMasterSqlSessionUtil().update(
				"IClienterBalanceRecordDao.updateByPrimaryKeySelective", record);
	}
	
	@Override
	public int updateStatusByOrderId(ClienterBalanceRecord record) {
		return getMasterSqlSessionUtil().update(
				"IClienterBalanceRecordDao.updateStatusByOrderId", record);
	}

	@Override
	public List<ClienterBalanceRecord> getRecordList(Long clienterId) {
		return getReadOnlySqlSessionUtil().selectList("IClienterBalanceRecordDao.getRecordList", clienterId);
	}

	@Override
	public PagedResponse<ClienterBalanceRecord> getRecordList(ClienterBlanceRecordReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IClienterBalanceRecordDao.getRecordListPage", req);
	}
}
