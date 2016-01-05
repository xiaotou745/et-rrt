package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.req.PagedBusinessReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;


@Repository
public class ClienterWithdrawFormDao extends DaoBase implements IClienterWithdrawFormDao {

	@Override
	public int insert(ClienterWithdrawForm record) {	
		return getMasterSqlSessionUtil().insert(
				"IClienterWithdrawFormDao.insert", record);
	}
	@Override
	public ClienterWithdrawForm selectByPrimaryKey(Long id) {
		return getMasterSqlSessionUtil().selectOne(
				"com.renrentui.api.dao.inter.IClienterWithdrawFormDao.selectByPrimaryKey", id);		
	}
	
	public ClienterWithdrawForm selectById(Long id)
	{
		return getMasterSqlSessionUtil().selectOne(
				"com.renrentui.api.dao.inter.IClienterWithdrawFormDao.selectById", id);		
	}
	
	
	@Override
	public	PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req){
		PagedResponse<ClienterWithdrawFormDM> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"IClienterWithdrawFormDao.getList",						 
						req);
		return model;
	}
	
	@Override
	public int updateByPrimaryKeySelective(ClienterWithdrawForm record) {	
		return getMasterSqlSessionUtil().update(
				"IClienterWithdrawFormDao.updateByPrimaryKeySelective", record);
	}
	
}
