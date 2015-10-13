package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.AppVersion;
import com.renrentui.renrenentity.req.VersionCheckReq;


/**
 * app 版本控制表
 * @author CaoHeYang
 * @date 20151013
 */
public interface IAppVersionService {
	/**
	 * app 版本控制 查询升级信息
	 * 
	 * @author CaoHeYang
	 * @date 20151013
	 * @param req
	 *            参数
	 * @return
	 */
	public AppVersion getVersionCheck(VersionCheckReq req);
}
