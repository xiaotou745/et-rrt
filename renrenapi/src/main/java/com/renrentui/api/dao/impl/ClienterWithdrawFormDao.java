package com.renrentui.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.api.common.DaoBase;
import com.renrentui.api.dao.inter.IAccountInfoDao;
import com.renrentui.api.dao.inter.IBusinessBalanceDao;
import com.renrentui.api.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.common.PagedResponse;
import com.renrentui.entity.AccountInfo;
import com.renrentui.entity.BusinessBalance;
import com.renrentui.entity.ClienterWithdrawForm;
import com.renrentui.entity.req.PagedAccountInfoReq;

@Repository
public class ClienterWithdrawFormDao extends DaoBase implements IClienterWithdrawFormDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ClienterWithdrawForm record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ClienterWithdrawForm record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterWithdrawForm selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().selectOne(
				"com.renrentui.api.dao.inter.IClienterWithdrawFormDao.selectByPrimaryKey", id);		
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterWithdrawForm record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterWithdrawForm record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
