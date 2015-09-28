package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedAccountInfoReq;



public interface IAccountInfoService {
	public  PagedResponse<AccountInfo>  queryAccount(PagedAccountInfoReq req);
	AccountInfo login(String username,String password);
	AccountInfo getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<AccountInfo> getByRoleID(int roleID);
}
