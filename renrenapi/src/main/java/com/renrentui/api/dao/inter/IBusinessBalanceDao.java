package com.renrentui.api.dao.inter;

import com.renrentui.entity.BusinessBalance;

public interface IBusinessBalanceDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessBalance record);

    int insertSelective(BusinessBalance record);

    BusinessBalance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessBalance record);

    int updateByPrimaryKey(BusinessBalance record);
}