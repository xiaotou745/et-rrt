package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.UpdatePwdReq;
import com.renrentui.renrenentity.req.PagedAccountInfoReq;



public interface IAccountInfoDao {
	public  PagedResponse<AccountInfo>  queryAccount(PagedAccountInfoReq req);
	AccountInfo login(String username,String password);
	AccountInfo getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<AccountInfo> getByRoleID(int roleID);
	int insert(AccountInfo account);
	int update(AccountInfo account);
	int updatePwd(UpdatePwdReq req);
}