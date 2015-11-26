package com.renrentui.renrenapi.dao.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.domain.CheckSubmitTask;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.domain.OrderChildInfoModel;
import com.renrentui.renrenentity.domain.OrderChildModel;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.OrderAuditReq;
import com.renrentui.renrenentity.req.OrderChildReq;
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
		String statement = "IOrderDao.addOrder";
		int res = getMasterSqlSessionUtil().insert(statement, order);
		return res;
	}
	/**
	 * 验证订单是否可以取消
	 */
	@Override
	public CheckCancelOrder checkCancelOrder(CancelTaskReq req) {
		
		return getMasterSqlSessionUtil().selectOne("IOrderDao.checkCancelOrder", req);
	}
	/**
	 * 取消订单
	 * 茹化肖
	 * 2015年9月30日13:50:17
	 */
	@Override
	public int cancelOrder(CancelTaskReq req) {
		return getMasterSqlSessionUtil().update("IOrderDao.cancelOrder", req);
	}
	/**
	 * 验证合同是否可以提交
	 */
	@Override
	public CheckSubmitTask checkOrderSubmit(SubmitTaskReq req) {
		return getMasterSqlSessionUtil().selectOne("IOrderDao.checkOrderSubmit", req);
	}
	/**
	 * 提交合同信息
	 * 更新订单状态
	 * 茹化肖
	 * 
	 */
	@Override
	public int submitOrder(SubmitTaskReq req) {
		return getMasterSqlSessionUtil().update("IOrderDao.submitOrder", req);
	}
	/**
	 * 管理后台-获取订单列表分页
	 */
	@Override
	public PagedResponse<OrderAudit> getOrderAuditList(PagedAuditorderReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IOrderDao.getOrderAuditList", req);
	}
	/**
	 * 订单审核
	 * 茹化肖
	 * 2015年10月10日11:10:27
	 * 
	 */
	@Override
	public int orderAudit(OrderAuditReq req) {
		return getMasterSqlSessionUtil().update("IOrderDao.orderAudit", req);
	}

	/**
	 * 超时取消订单服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	@Override
	public void outTimeCanelOrder() {
		String statement = "IOrderDao.outTimeCanelOrder";
		int count= getMasterSqlSessionUtil().update(statement);
	}

	/**
	 * 获取合同头信息
	 */
	@Override
	public OrderChildInfoModel getOrderInfo(OrderChildReq req) {
		
		String statement = "IOrderDao.getOrderInfo";
		return getReadOnlySqlSessionUtil().selectOne(statement,req);
	}
	/**
	 * 获取合同控件信息
	 */
	@Override
	public ArrayList<OrderChildModel> getOrderChildList(OrderChildReq req) {
		String statement = "IOrderDao.getOrderChildList";
		List<OrderChildModel> list= getReadOnlySqlSessionUtil().selectList(statement,req);
		if(list.size()>0)
		{
			return (ArrayList<OrderChildModel>)list;
		}
		return null;
	}

	@Override
	public Double getOrderTotalAmount(Long taskId) {
		return getReadOnlySqlSessionUtil().selectOne("IOrderDao.getOrderTotalAmount",taskId);
	}
	
	
}
