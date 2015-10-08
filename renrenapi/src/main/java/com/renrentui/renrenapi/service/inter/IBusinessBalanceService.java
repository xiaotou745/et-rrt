package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.req.BusinessBalanceReq;

public interface IBusinessBalanceService {
	int updateBalanceByBusinessId(BusinessBalanceReq record);
}
