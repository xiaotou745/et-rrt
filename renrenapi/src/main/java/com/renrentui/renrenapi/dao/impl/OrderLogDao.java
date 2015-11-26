package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IOrderLogDao;
import com.renrentui.renrenentity.OrderLog;

@Repository
public class OrderLogDao extends DaoBase implements IOrderLogDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(OrderLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderLog selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderLog record) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 记录订单表操作日志
	 * 操作类型：1抢单2提交审核3重复提交审核4取消订单5超时取消（系统）
	 */
	@Override
	public int addOrderLog(OrderLog log) {
		return	getMasterSqlSessionUtil().insert("IOrderLogDao.addOrderLog", log);
	}

}
