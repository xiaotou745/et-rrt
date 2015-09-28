package com.renrentui.api.service.inter;

import java.util.List;

import com.renrentui.entity.AccountAuth;
import com.renrentui.entity.BusinessBalance;
import com.renrentui.entity.ClienterBalance;
import com.renrentui.entity.ClienterWithdrawForm;

public interface IClienterBalanceService {
	

	ClienterBalance selectByPrimaryKey(Long id);
}
