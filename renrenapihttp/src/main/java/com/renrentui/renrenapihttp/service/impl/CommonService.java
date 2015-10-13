package com.renrentui.renrenapihttp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.IAppVersionService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.ICommonService;
import com.renrentui.renrencore.enums.VersionCheckReturnEnum;
import com.renrentui.renrenentity.AppVersion;
import com.renrentui.renrenentity.req.VersionCheckReq;

/**
 * 通用模块
 * 
 * @author CaoHeYang
 * @date 20150909
 */
@Service
public class CommonService implements ICommonService {
	@Autowired
	private IAppVersionService appVersionService;
	/**
	 * 版本升级检测
	 * 
	 * @author CaoHeYang
	 * @date 20151013
	 * @return
	 */
	@Override
	public HttpResultModel<AppVersion> versionCheck(VersionCheckReq para) {
		HttpResultModel<AppVersion> result = new HttpResultModel<AppVersion>();
		if (para == null || para.getPlatForm() == 0) {
			return result.setCode(VersionCheckReturnEnum.NoPlatForm.value()).setMsg(VersionCheckReturnEnum.NoPlatForm.desc());
		}
		if (para.getUserType() == 0) {
			return result.setCode(VersionCheckReturnEnum.NoUserType.value()).setMsg(VersionCheckReturnEnum.NoUserType.desc());
		}
        AppVersion appVersion = appVersionService.getVersionCheck(para);
        if (appVersion == null)
        {
             return result.setCode(VersionCheckReturnEnum.NoData.value()).setMsg(VersionCheckReturnEnum.NoData.desc());
        }
        return result.setCode(VersionCheckReturnEnum.Success.value()).setMsg(VersionCheckReturnEnum.Success.desc()).setData(appVersion);
	}

}
