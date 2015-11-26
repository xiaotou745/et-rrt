package com.renrentui.renrenapi.dao.impl;

import java.util.List;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.BusinessBalanceReq;
import com.renrentui.renrenentity.req.PagedBusinessReq;
import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IBusinessBalanceDao;
import com.renrentui.renrenapi.dao.inter.IBusinessDao;
import com.renrentui.renrenapi.dao.inter.IBusinessRechargeDao;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.BusinessRecharge;
@Repository
public class BusinessRechargeDao extends DaoBase implements IBusinessRechargeDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessRecharge record) {
		return getMasterSqlSessionUtil().insert(
				"IBusinessRechargeDao.insert", record);
	}

	@Override
	public int insertSelective(BusinessRecharge record) {
	return 0;
	}

	@Override
	public BusinessRecharge selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessRecharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusinessRecharge record) {
		// TODO Auto-generated method stub
		return 0;
	}


	
}
