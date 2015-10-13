package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IAppVersionDao;
import com.renrentui.renrenentity.AppVersion;
import com.renrentui.renrenentity.req.VersionCheckReq;

/**
 * app 版本控制表
 * 
 * @author CaoHeYang
 * @date 20151013
 */
@Repository
public class AppVersionDao extends DaoBase  implements IAppVersionDao {
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
		return getReadOnlySqlSessionUtil().
				selectOne("com.renrentui.renrenapi.dao.inter.IAppVersionDao.getVersionCheck",req);
	}

}
