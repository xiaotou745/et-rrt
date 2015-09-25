package com.renrentui.api.dao.inter;

import java.util.List;




import com.renrentui.common.PagedResponse;
import com.renrentui.entity.AccountInfo;
import com.renrentui.entity.req.PagedAccountReq;


public interface IAccountInfoDao {
	public  PagedResponse<AccountInfo>  queryAccount(PagedAccountReq req);
	AccountInfo login(String username,String password);
	AccountInfo getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<AccountInfo> getByRoleID(int roleID);
}