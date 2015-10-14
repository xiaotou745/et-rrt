package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IBusinessLogDao;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenentity.BusinessLog;
import com.renrentui.renrenentity.ClienterLog;
@Repository
public class BusinessLogDao extends DaoBase implements IBusinessLogDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessLog record) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.IBusinessLogDao.insert", record);
	}

	@Override
	public int insertSelective(BusinessLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessLog selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusinessLog record) {
		// TODO Auto-generated method stub
		return 0;
	}



}
