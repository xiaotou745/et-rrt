package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IAppVersionDao;
import com.renrentui.renrenapi.service.inter.IAppVersionService;
import com.renrentui.renrenentity.AppVersion;
import com.renrentui.renrenentity.req.VersionCheckReq;


/**
 * app 版本控制表
 * 
 * @author CaoHeYang
 * @date 20151013
 */
@Service
public class AppVersionService implements IAppVersionService {
	@Autowired
	private IAppVersionDao appVersionDao;
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
		return appVersionDao.getVersionCheck(req);
	}
	
}
