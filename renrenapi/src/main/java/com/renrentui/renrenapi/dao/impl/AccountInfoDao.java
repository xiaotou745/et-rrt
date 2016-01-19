package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IAccountInfoDao;
import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedAccountInfoReq;
import com.renrentui.renrenentity.req.UpdatePwdReq;

@Repository
public class AccountInfoDao extends DaoBase implements IAccountInfoDao {
	// 查询所有管理后台用户列表
	@Override
	public PagedResponse<AccountInfo> queryAccount(PagedAccountInfoReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"IAccountInfoDao.query", req);
	}

	@Override
	public AccountInfo login(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		return getReadOnlySqlSessionUtil().selectOne(
				"IAccountInfoDao.login", params);
	}

	@Override
	public AccountInfo getByID(int userID) {
		return getReadOnlySqlSessionUtil().selectOne(
				"IAccountInfoDao.getByID", userID);
	}

	@Override
	public int updateRoleID(int userID, int newRoleID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("newRoleID", newRoleID);
		return getMasterSqlSessionUtil().update(
				"IAccountInfoDao.updateRoleID", params);
	}

	@Override
	public List<AccountInfo> getByRoleID(int roleID) {
		return getReadOnlySqlSessionUtil().selectList(
				"IAccountInfoDao.getByRoleID", roleID);
	}

	@Override
	public int insert(AccountInfo account) {
		return getMasterSqlSessionUtil().insert(
				"IAccountInfoDao.insert", account);
	}

	@Override
	public int update(AccountInfo account) {
		return getMasterSqlSessionUtil().update(
				"IAccountInfoDao.update", account);
	}

	@Override
	public int updatePwd(UpdatePwdReq req) {
		return getMasterSqlSessionUtil().update(
				"IAccountInfoDao.updatePwd", req);
	}

}
