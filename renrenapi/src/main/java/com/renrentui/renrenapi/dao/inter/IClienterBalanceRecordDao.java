package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterBalanceRecord;


public interface IClienterBalanceRecordDao {


    int insert(ClienterBalanceRecord record);


    ClienterBalanceRecord selectByPrimaryKey(Long id);
    
    ClienterBalanceRecord selectByOrderId(Long OrderId);

    int updateByPrimaryKeySelective(ClienterBalanceRecord record);
    
    int updateStatusByOrderId(ClienterBalanceRecord record); 
	List<ClienterBalanceRecord> getRecordList(Long clienterId);
}