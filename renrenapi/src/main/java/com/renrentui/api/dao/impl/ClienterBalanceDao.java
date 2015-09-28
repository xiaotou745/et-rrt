package com.renrentui.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.api.common.DaoBase;
import com.renrentui.api.dao.inter.IAccountInfoDao;
import com.renrentui.api.dao.inter.IBusinessBalanceDao;
import com.renrentui.api.dao.inter.IClienterBalanceDao;
import com.renrentui.api.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.common.PagedResponse;
import com.renrentui.entity.AccountInfo;
import com.renrentui.entity.BusinessBalance;
import com.renrentui.entity.ClienterBalance;
import com.renrentui.entity.ClienterWithdrawForm;
import com.renrentui.entity.req.PagedAccountInfoReq;

@Repository
public class ClienterBalanceDao extends DaoBase implements IClienterBalanceDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ClienterBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ClienterBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterBalance selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().selectOne(
				"com.renrentui.api.dao.inter.IClienterBalanceDao.selectByPrimaryKey", id);		
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}	
	

}
