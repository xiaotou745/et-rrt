package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IOrderChildDao;
import com.renrentui.renrenentity.OrderChild;
/**
 * v1.0.2需求删除
 * @author ofmyi_000
 *
 */
@Repository
public class OrderChildDao extends DaoBase implements IOrderChildDao{
//	/**
//	 * 
//	 * 插入合同信息
//	 * 茹化肖
//	 * 2015年10月8日13:11:20
//	 */
//	@Override
//	public int insert(OrderChild record) {
//		String statement="IOrderChildDao.insert";
//		return getMasterSqlSessionUtil().insert(statement, record);
//	}
//	
//	/**
//	 * 删除之前提交的合同信息
//	 * 茹化肖
//	 * 2015年10月8日13:11:25
//	 */
//	@Override
//	public int deleteOrderChild(Long orderId) {
//		String statement="IOrderChildDao.deleteOrderChild";
//		HashMap<String, Object> map=new HashMap<String, Object> ();
//		map.put("orderid", orderId);
//		return getMasterSqlSessionUtil().update(statement, map);
//	}

}
