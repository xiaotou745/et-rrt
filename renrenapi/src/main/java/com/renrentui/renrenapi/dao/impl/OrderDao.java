package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenentity.Order;
@Repository
public class OrderDao extends DaoBase implements IOrderDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 为C端插入订单
	 * 茹化肖
	 * 2015年9月30日09:52:21
	 */
	@Override
	public int addOrder(Order order) {
		String statement = "com.renrentui.renrenapi.dao.inter.IOrderDao.addOrder";
		int res = getMasterSqlSessionUtil().insert(statement, order);
		return res;
	}

}
