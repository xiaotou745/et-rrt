package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenapi.dao.inter.IOrderLogDao;
import com.renrentui.renrenapi.service.inter.IOrderService;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.req.PagedAuditorderReq;
@Service
public class OrderService implements IOrderService{
	@Autowired
	private IOrderDao orderDao;	
	@Autowired
	private IOrderLogDao orderLogDao;
	
	/**
	 * 获取订单审核列表
	 * 茹化肖
	 * 2015年10月9日15:01:25
	 */
	@Override
	public PagedResponse<OrderAudit> getOrderAuditList(PagedAuditorderReq req) {
		return orderDao.getOrderAuditList(req);
	}
}
