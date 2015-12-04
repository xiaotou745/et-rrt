package com.renrentui.renrenapi.dao.inter;

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
public interface IAppVersionDao {
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
	public  PagedResponse<AppVersion>  queryAppVersion(PagedRequestBase req);

	AppVersion getByID(int id);
	int insert(AppVersion record);
	int update(AppVersion record);
	int cancel(int id,String userName);
	int modify();
}
