package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;


public interface IClienterWithdrawFormService {	

	
	int Add(ClienterWithdrawForm record) ;
	
	int AuditPass(ClienterWithdrawForm record) ;
	
	int AuditRefuse(ClienterWithdrawForm record) ;
	
	PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req);
}
