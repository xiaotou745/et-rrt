package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.OrderLog;

public interface IOrderLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderLog record);

    int insertSelective(OrderLog record);

    OrderLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderLog record);

    int updateByPrimaryKey(OrderLog record);
}