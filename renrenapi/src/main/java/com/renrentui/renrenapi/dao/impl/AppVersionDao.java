package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IAppVersionDao;
import com.renrentui.renrenentity.AppVersion;
import com.renrentui.renrenentity.common.PagedRequestBase;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.VersionCheckReq;

/**
 * app 版本控制表
 * 
 * @author CaoHeYang
 * @date 20151013
 */
@Repository
public class AppVersionDao extends DaoBase implements IAppVersionDao {
	/**
	 * app 版本控制 查询升级信息
	 * 
	 * @author CaoHeYang
	 * @date 20151013
	 * @param req
	 *            参数
	 * @return
	 */
	@Override
	public AppVersion getVersionCheck(VersionCheckReq req) {
		return getReadOnlySqlSessionUtil().selectOne(
				"IAppVersionDao.getVersionCheck", req);
	}

	@Override
	public PagedResponse<AppVersion> queryAppVersion(PagedRequestBase req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"IAppVersionDao.queryAppVersion", req);
	}

	@Override
	public AppVersion getByID(int id) {
		return getReadOnlySqlSessionUtil().selectOne("IAppVersionDao.getByID",
				id);
	}

	@Override
	public int insert(AppVersion record) {
		return getMasterSqlSessionUtil()
				.insert("IAppVersionDao.insert", record);
	}

	@Override
	public int update(AppVersion record) {
		return getMasterSqlSessionUtil()
				.update("IAppVersionDao.update", record);
	}

	@Override
	public int cancel(int id, String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("userName", userName);
		return getMasterSqlSessionUtil()
				.update("IAppVersionDao.cancel", params);
	}

	@Override
	public int modify() {
		return getMasterSqlSessionUtil().update("IAppVersionDao.modify");
	}

}
