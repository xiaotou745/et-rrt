package com.renrentui.renrenapi.dao.inter;

import java.awt.List;
import java.util.ArrayList;

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

public interface IOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    int addOrder(Order order);
    CheckCancelOrder checkCancelOrder(CancelTaskReq req);
    
    int cancelOrder(CancelTaskReq req);
    
    CheckSubmitTask checkOrderSubmit(SubmitTaskReq req);
    
    int submitOrder(SubmitTaskReq req);
    
    PagedResponse<OrderAudit> getOrderAuditList(PagedAuditorderReq req);
    
    /**
	 * 超时取消订单服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
    void outTimeCanelOrder();
	 
	int orderAudit(OrderAuditReq req);
	
	OrderChildInfoModel getOrderInfo(OrderChildReq req);
	
	ArrayList<OrderChildModel> getOrderChildList(OrderChildReq req);

}