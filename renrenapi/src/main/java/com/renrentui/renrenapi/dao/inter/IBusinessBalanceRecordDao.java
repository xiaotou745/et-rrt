package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.BusinessBalanceRecord;

public interface IBusinessBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessBalanceRecord record);

    int insertSelective(BusinessBalanceRecord record);

    BusinessBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessBalanceRecord record);

    int updateByPrimaryKey(BusinessBalanceRecord record);
}