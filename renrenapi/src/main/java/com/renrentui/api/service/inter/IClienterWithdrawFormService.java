package com.renrentui.api.service.inter;

import java.util.List;

import com.renrentui.entity.AccountAuth;
import com.renrentui.entity.BusinessBalance;
import com.renrentui.entity.ClienterWithdrawForm;

public interface IClienterWithdrawFormService {
	

	ClienterWithdrawForm selectByPrimaryKey(Long id);
}
