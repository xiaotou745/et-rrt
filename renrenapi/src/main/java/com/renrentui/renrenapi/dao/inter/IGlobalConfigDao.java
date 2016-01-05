package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.GlobalConfig;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.GlobalConfigModel;
import com.renrentui.renrenentity.req.ConfigSaveReq;
import com.renrentui.renrenentity.req.PagedGlobalConfigReq;


public interface IGlobalConfigDao {
	List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id);
	GlobalConfigModel getGlobalConfigByPrimaryId(Integer id);
	int update(ConfigSaveReq par);
	int insert(GlobalConfig par);	
	PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search);
	String getValueByName(String name);
//	GlobalGroupConfigModel GlobalConfigMethod(int groupId);
	
}