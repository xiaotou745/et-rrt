package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterBalanceRecord;

public interface IClienterBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterBalanceRecord record);

    int insertSelective(ClienterBalanceRecord record);

    ClienterBalanceRecord selectByPrimaryKey(Long id);
    
    ClienterBalanceRecord selectByOrderId(Long OrderId);

    int updateByPrimaryKeySelective(ClienterBalanceRecord record);

    int updateByPrimaryKey(ClienterBalanceRecord record);
    
    int updateStatusByOrderId(ClienterBalanceRecord record); 
}