package com.renrentui.renrenapi.service.inter;

import javax.servlet.http.HttpServletRequest;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.domain.OrderChildInfoModel;
import com.renrentui.renrenentity.req.CancelOrderReq;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.OrderAuditReq;
import com.renrentui.renrenentity.req.OrderChildReq;
import com.renrentui.renrenentity.req.PagedAuditorderReq;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;

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
//	public void outTimeCanelOrder();
	
//	public OrderChildInfoModel getOrderChildInfo(OrderChildReq req);
	
	public String downLoadOrderInfo(TaskDatumDetailReq req);
//	public int cancelOrder(CancelTaskReq req);
//	public Double getOrderTotalAmount(Long taskId);
}
