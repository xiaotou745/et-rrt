package com.renrentui.renrenapi.dao.impl;


import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.domain.CheckSubmitTask;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.PagedAuditorderReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
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
	/**
	 * 验证订单是否可以取消
	 */
	@Override
	public CheckCancelOrder checkCancelOrder(CancelTaskReq req) {
		
		return getMasterSqlSessionUtil().selectOne("com.renrentui.renrenapi.dao.inter.IOrderDao.checkCancelOrder", req);
	}
	/**
	 * 取消订单
	 * 茹化肖
	 * 2015年9月30日13:50:17
	 */
	@Override
	public int cancelOrder(CancelTaskReq req) {
		return getMasterSqlSessionUtil().update("com.renrentui.renrenapi.dao.inter.IOrderDao.cancelOrder", req);
	}
	/**
	 * 验证合同是否可以提交
	 */
	@Override
	public CheckSubmitTask checkOrderSubmit(SubmitTaskReq req) {
		return getMasterSqlSessionUtil().selectOne("com.renrentui.renrenapi.dao.inter.IOrderDao.checkOrderSubmit", req);
	}
	/**
	 * 提交合同信息
	 * 更新订单状态
	 * 茹化肖
	 * 
	 */
	@Override
	public int submitOrder(SubmitTaskReq req) {
		return getMasterSqlSessionUtil().update("com.renrentui.renrenapi.dao.inter.IOrderDao.submitOrder", req);
	}
	/**
	 * 管理后台-获取订单列表分页
	 */
	@Override
	public PagedResponse<OrderAudit> getOrderAuditList(PagedAuditorderReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("com.renrentui.renrenapi.dao.inter.IOrderDao.getOrderAuditList", req);
	}

	/**
	 * 超时取消订单服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	@Override
	public void outTimeCanelOrder() {
		String statement = "com.renrentui.renrenapi.dao.inter.IOrderDao.outTimeCanelOrder";
		int count= getMasterSqlSessionUtil().update(statement);
	}
}
