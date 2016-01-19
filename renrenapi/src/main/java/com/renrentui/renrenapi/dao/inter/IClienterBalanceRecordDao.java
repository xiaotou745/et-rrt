package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.SubmissionTip;
import com.renrentui.renrenentity.req.ClienterBlanceRecordReq;
import com.renrentui.renrenentity.req.GetIncomeReq;
import com.renrentui.renrenentity.req.GetUserCReq;


public interface IClienterBalanceRecordDao {


    int insert(ClienterBalanceRecord record);


    ClienterBalanceRecord selectByPrimaryKey(Long id);
    
    ClienterBalanceRecord selectByOrderId(Long OrderId);
    ClienterBalanceRecord selBalanceByOrderId(Long OrderId);
    int updateByPrimaryKeySelective(ClienterBalanceRecord record);
    
    int updateStatusByOrderId(ClienterBalanceRecord record); 
	List<ClienterBalanceRecord> getRecordList(GetIncomeReq req);
	
	PagedResponse<ClienterBalanceRecord> getRecordList(ClienterBlanceRecordReq req);
	
	List<SubmissionTip> getSubmissionTip(Long orderId);


	int handChargeinsert(ClienterBalanceRecord cbrHandCharge);
}