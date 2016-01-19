package com.renrentui.renrenapi.service.inter;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 

import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchCallBackModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormExcel;
import com.renrentui.renrenentity.req.AlipayBatchReq;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;


public interface IClienterWithdrawFormService {	

	/**
	 * 用户提现
	 * 胡灵波
	 * 2015年9月28日 16:58:06
	 * @param req
	 * @return
	 */
	WithdrawState WithdrawC(ClienterBalanceReq req);
	
	int Add(ClienterWithdrawForm record) ;
	
	int AuditPass(ClienterWithdrawForm record) ;
	
	int AuditRefuse(ClienterWithdrawForm record) ;
	
	PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req);

	String AlipayBatchTransfer(AlipayBatchReq alipayBatchReq);
	//HttpServletRequest request
	String AliBatchNotifyTransferCallback(HttpServletRequest request);
	
	 boolean AliBatchNotifyTransferCallbackBusinessDeal(
			AlipayBatchCallBackModel alipayBatchCallBackModel);

	List<ClienterWithdrawFormExcel> exportWithdraw(PagedClienterWithdrawFormReq req);
}
