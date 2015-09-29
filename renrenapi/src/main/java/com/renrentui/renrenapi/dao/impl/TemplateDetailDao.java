package com.renrentui.renrenapi.dao.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.renrentui.renrenentity.domain.ControlInfo;
import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenentity.TemplateDetail;
@Repository
public class TemplateDetailDao extends DaoBase implements ITemplateDetailDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getMasterSqlSessionUtil().delete(
				"com.renrentui.renrenapi.dao.inter.ITemplateDetailDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(TemplateDetail record) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.ITemplateDetailDao.insert", record);
	}

	@Override
	public int insertList(List<TemplateDetail> recordList) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.ITemplateDetailDao.insertList", recordList);
	}
	/**
	 * 获取合同的详细控件信息
	 * 茹化肖
	 * 2015年9月29日13:39:58
	 * 
	 */
	@Override
	public ArrayList<ControlInfo> getTemplateList(Long templateId) {
		String statement = "com.renrentui.renrenapi.dao.inter.ITemplateDetailDao.getTemplateList";
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("templateId", templateId);
		ArrayList<ControlInfo> res = (ArrayList) getReadOnlySqlSessionUtil().selectList(statement, map);
		return res;
	}

}
