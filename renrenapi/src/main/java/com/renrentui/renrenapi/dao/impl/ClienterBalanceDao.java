package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenentity.ClienterBalance;


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
		return getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.IClienterBalanceDao.selectByPrimaryKey", id);	
		        
		         
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
	
	@Override
	public int updateMoneyByKey(ClienterBalance record)
	{
/*		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateMoneyById",
						record);*/
		return 0;
	}

}