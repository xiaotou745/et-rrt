package com.renrentui.api.service.inter;

import java.util.List;

import com.renrentui.common.PagedResponse;
import com.renrentui.entity.AccountInfo;
import com.renrentui.entity.req.PagedAccountInfoReq;



public interface IAccountInfoService {
	public  PagedResponse<AccountInfo>  queryAccount(PagedAccountInfoReq req);
	AccountInfo login(String username,String password);
	AccountInfo getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<AccountInfo> getByRoleID(int roleID);
}
