package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.GlobalConfig;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.GlobalConfigModel;
import com.renrentui.renrenentity.req.ConfigSaveReq;
import com.renrentui.renrenentity.req.PagedGlobalConfigReq;

public interface IGlobalConfigService {
	 int update(ConfigSaveReq par);
	 String getConfigValueByKey(int groupID,String key);
	 int insert(GlobalConfig par);
	 PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search);
	 /**
	  * 通过名称获取键值
	  * @param nema
	  * @return
	  */
	 String getValueByName(String nema);
}
