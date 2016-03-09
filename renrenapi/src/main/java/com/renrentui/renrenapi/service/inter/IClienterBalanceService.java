package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterBalance;


public interface IClienterBalanceService {
	

	ClienterBalance selectByPrimaryKey(Long id);

	boolean fetchRedbag(int clienterId,String openid,int activityid);
	
}
