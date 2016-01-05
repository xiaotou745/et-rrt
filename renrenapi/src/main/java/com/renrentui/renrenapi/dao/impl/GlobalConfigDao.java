package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IGlobalConfigDao;
import com.renrentui.renrenentity.GlobalConfig;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.GlobalConfigModel;
import com.renrentui.renrenentity.req.ConfigSaveReq;
import com.renrentui.renrenentity.req.PagedGlobalConfigReq;


@Repository
public class GlobalConfigDao extends DaoBase implements IGlobalConfigDao {
	/*
	 * 获取全局配置
	 */
	@Override
	public List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) {

		String statement = "IGlobalConfigDao.getGlobalGroupConfig";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupid", id);
		List<GlobalConfigModel> model = getReadOnlySqlSessionUtil().selectList(
				statement, paramMap);
		return model;

	}

	/*
	 * 保存全局变量的值
	 */
	@Override
	public int update(ConfigSaveReq par) {
		String statement = "IGlobalConfigDao.saveConfigValue";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", par.getId());
		paramMap.put("parvalue", par.getConfigValue());
		return getMasterSqlSessionUtil().update(statement, paramMap);
	}

	/*
	 * 添加新的全局配置
	 */
	@Override
	public int insert(GlobalConfig par) {
		String statement = "IGlobalConfigDao.insert";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("keyname", par.getKeyname());
		paramMap.put("value", par.getValue());
		paramMap.put("remark", par.getRemark());
		
		return getMasterSqlSessionUtil().insert(statement, paramMap);
	}

	@Override
	public PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search) {
		PagedResponse<GlobalConfigModel> result = new PagedResponse<GlobalConfigModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"IGlobalConfigDao.getPagedGlobalConfigModels", search);
		return result;
	}

	@Override
	public GlobalConfigModel getGlobalConfigByPrimaryId(Integer id) {
		GlobalConfigModel model = getReadOnlySqlSessionUtil().selectOne(
				"IGlobalConfigDao.getGlobalConfigByPrimaryId", id);
		return model;
	}

	@Override
	public String getValueByName(String name) {
		return getReadOnlySqlSessionUtil().selectOne("IGlobalConfigDao.getValueByName", name);
	}

   
}