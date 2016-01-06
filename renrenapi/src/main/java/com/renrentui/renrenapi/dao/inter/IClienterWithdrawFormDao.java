package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.domain.AlipayClienterWithdrawModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.domain.ClienterWithdrawLog;
import com.renrentui.renrenentity.req.AlipayBatchReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;
import com.renrentui.renrenentity.req.UpdateAlipayBatchReq;

public interface IClienterWithdrawFormDao {

    int insert(ClienterWithdrawForm record);


    ClienterWithdrawForm selectByPrimaryKey(Long id);
    
    ClienterWithdrawForm selectById(Long id);

    int updateByPrimaryKeySelective(ClienterWithdrawForm record);
    
    PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req);


	int CheckAlipayBatch(AlipayBatchModel alipayBatchModel);


	List<AlipayClienterWithdrawModel> GetWithdrawListForAlipay(
			AlipayBatchReq alipayBatchReq);


	int InsertLog(ClienterWithdrawLog clienterWithLog);


	int UpdateAlipayBatchNo(UpdateAlipayBatchReq updateAlipayBatchReq);


	int InsertAlipayBatch(AlipayBatchModel insertAlipayBatchModel);


	int UpdateAlipayBatchForAgain(AlipayBatchModel updateAlipayBatchModel); 
}