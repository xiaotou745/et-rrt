package com.renrentui.renrenapi.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;


import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenentity.TemplateDetail;
import com.renrentui.renrenentity.domain.ControlInfo;
import com.renrentui.renrenentity.domain.TaskDetail;
@Repository
public class TemplateDetailDao extends DaoBase implements ITemplateDetailDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TemplateDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TemplateDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TemplateDetail selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TemplateDetail record) {
		// TODO Auto-generated method stub
		return 0;
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
