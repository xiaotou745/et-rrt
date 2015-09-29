package com.renrentui.renrenapi.dao.impl;

import java.util.List;
import java.util.HashMap;
import org.springframework.stereotype.Repository;
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
				"com.renrentui.renrenapi.dao.inter.IBusinessDao.insert", record);
	}

	@Override
	public int insertSelective(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Business selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Business> getAllList() {
		return getReadOnlySqlSessionUtil().selectList("com.renrentui.renrenapi.dao.inter.IBusinessDao.getAllList");
	}
	@Override
	public	PagedResponse<Business> getBusinessList(PagedBusinessReq req)
	{
		PagedResponse<Business> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"com.renrentui.renrenapi.dao.inter.IBusinessDao.getBusinessList",						 
						req);
		return model;
	}
}
