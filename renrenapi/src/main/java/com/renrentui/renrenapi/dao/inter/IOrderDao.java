package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.domain.CheckSubmitTask;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.OrderAuditReq;
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
    
    int orderAudit(OrderAuditReq req);
}