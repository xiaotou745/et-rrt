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
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
@Repository
public class BusinessBalanceDao extends DaoBase implements IBusinessBalanceDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessBalance record) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.IBusinessBalanceDao.insert", record);
	}

	@Override
	public int insertSelective(BusinessBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessBalance selectByBusinessId(Long businessId) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.IBusinessBalanceDao.selectByBusinessId", businessId);
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int updateByPrimaryKey(BusinessBalance record) {
		return 0;		          
	}
	
	/**
	 * 列新商户余额
	 * @author hulingbo
	 * @Date 2015年9月30日 10:34:34
	 * @param search 查询条件实体
	 * @return	
	 */	
	@Override
	public int updateBalanceByBusinessId(BusinessBalanceReq record) {
		return getMasterSqlSessionUtil().update(
				"com.renrentui.renrenapi.dao.inter.IBusinessBalanceDao.updateByBusinessId", record);
	}
	
}
