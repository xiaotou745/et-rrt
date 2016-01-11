package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.domain.ClienterFinanceAcountModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawLogModel;
import com.renrentui.renrenentity.req.BindAliPayReq;

public interface IClienterFinanceAcountService {
	int bindAliPay(BindAliPayReq req);

	boolean ClienterWithdrawPayOk(ClienterWithdrawLogModel clienterWithdrawLogModel);

	ClienterFinanceAcountModel GetClienterFinanceAccount(Long withdrawId);

	boolean ClienterWithdrawPayFail(ClienterWithdrawLogModel cwlModel);
}
