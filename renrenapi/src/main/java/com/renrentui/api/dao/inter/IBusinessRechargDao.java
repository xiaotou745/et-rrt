package com.renrentui.api.dao.inter;

import com.renrentui.entity.BusinessRecharge;

public interface IBusinessRechargDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessRecharge record);

    int insertSelective(BusinessRecharge record);

    BusinessRecharge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessRecharge record);

    int updateByPrimaryKey(BusinessRecharge record);
}