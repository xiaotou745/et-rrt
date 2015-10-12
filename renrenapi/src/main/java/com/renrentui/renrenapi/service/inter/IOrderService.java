package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.req.OrderAuditReq;
import com.renrentui.renrenentity.req.PagedAuditorderReq;

/**
 * 订单相关接口
 * @author ofmyi_000
 *
 */
public interface IOrderService {
	PagedResponse<OrderAudit> getOrderAuditList(PagedAuditorderReq req);

	int orderAudit(OrderAuditReq req);

	/**
	 * 超时取消订单服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	public void outTimeCanelOrder();

}
