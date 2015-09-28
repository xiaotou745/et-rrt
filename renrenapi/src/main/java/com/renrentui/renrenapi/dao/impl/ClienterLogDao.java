package com.renrentui.renrenapi.dao.impl;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenentity.ClienterLog;

public class ClienterLogDao extends DaoBase implements IClienterLogDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 
	 * 记录骑士操作记录
	 * 茹化肖
	 * 2015年9月28日16:58:27
	 */
	@Override
	public int insert(ClienterLog record) {
		getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.IClienterLogDao.insert", record);
		return 0;
	}

	@Override
	public int insertSelective(ClienterLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterLog selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
