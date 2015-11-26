package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.req.ClienterBalanceReq;


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
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}





	@Override
	public int updateByPrimaryKey(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
