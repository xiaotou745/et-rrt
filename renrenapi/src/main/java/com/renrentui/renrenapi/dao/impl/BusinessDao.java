package com.renrentui.renrenapi.dao.impl;

import java.util.List;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.BusinessModel;
import com.renrentui.renrenentity.req.PagedBusinessReq;
import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IBusinessDao;
import com.renrentui.renrenentity.Business;
@Repository
public class BusinessDao extends DaoBase implements IBusinessDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Business record) {
		return getMasterSqlSessionUtil().insert(
				"IBusinessDao.insert", record);
	}

	@Override
	public int insertSelective(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Business selectById(Long id) {
		return getReadOnlySqlSessionUtil().selectOne(
				"IBusinessDao.selectById", id);
	}

	@Override
	public int updateByPrimaryKeySelective(Business record) {
		return getMasterSqlSessionUtil().update(
				"IBusinessDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Business> getAllList() {
		return getReadOnlySqlSessionUtil().selectList("IBusinessDao.getAllList");
	}
	@Override
	public	PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req){
		PagedResponse<BusinessModel> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"IBusinessDao.getBusinessList",						 
						req);
		return model;
	}
}
