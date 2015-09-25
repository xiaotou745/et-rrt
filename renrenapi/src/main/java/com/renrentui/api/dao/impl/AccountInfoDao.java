package com.renrentui.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.api.common.DaoBase;
import com.renrentui.api.dao.inter.IAccountInfoDao;
import com.renrentui.common.PagedResponse;
import com.renrentui.entity.AccountInfo;
import com.renrentui.entity.req.PagedAccountReq;

@Repository
public class AccountInfoDao extends DaoBase implements IAccountInfoDao {
	// 查询所有管理后台用户列表
	@Override
	public PagedResponse<AccountInfo> queryAccount(PagedAccountReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.renrentui.renrenapi.dao.inter.IAccountDao.query", req);
	}

	@Override
	public AccountInfo login(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		return getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.IAccountDao.login", params);
	}

	@Override
	public AccountInfo getByID(int userID) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.IAccountDao.getByID", userID);
	}

	@Override
	public int updateRoleID(int userID, int newRoleID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("newRoleID", newRoleID);
		return getMasterSqlSessionUtil().update(
				"com.renrentui.renrenapi.dao.inter.IAccountDao.updateRoleID", params);
	}

	@Override
	public List<AccountInfo> getByRoleID(int roleID) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.renrentui.renrenapi.dao.inter.IAccountDao.getByRoleID", roleID);
	}

}
