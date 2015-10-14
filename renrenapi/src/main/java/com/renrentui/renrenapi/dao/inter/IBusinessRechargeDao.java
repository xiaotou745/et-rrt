package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.BusinessRecharge;

public interface IBusinessRechargeDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessRecharge record);

    int insertSelective(BusinessRecharge record);

    BusinessRecharge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessRecharge record);

    int updateByPrimaryKey(BusinessRecharge record);
}