package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterFinanceAcountDao;
import com.renrentui.renrenentity.ClienterFinanceAcount;

@Repository
public class ClienterFinanceAcountDao extends DaoBase implements
		IClienterFinanceAcountDao {

	/**
	 * 绑定支付宝账号，有就更新，没有则insert
	 */
	@Override
	public int insertSelective(ClienterFinanceAcount record) {
		return getMasterSqlSessionUtil().insert(
				"IClienterFinanceAcountDao.insertSelective", record);
	}

	@Override
	public ClienterFinanceAcount select(long clienterid, int accounttype) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("clienterid", clienterid);
		paramMap.put("accounttype", accounttype);
		return getReadOnlySqlSessionUtil().selectOne(
				"IClienterFinanceAcountDao.select", paramMap);
	}

	@Override
	public int updateSelective(ClienterFinanceAcount record) {
		return getMasterSqlSessionUtil().update(
				"IClienterFinanceAcountDao.updateSelective", record);
	}

}
