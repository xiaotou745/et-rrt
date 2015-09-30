package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.req.CancelTaskReq;

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
}