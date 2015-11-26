package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;



import org.springframework.stereotype.Repository;

import com.renrentui.renrenentity.domain.ControlInfo;
import com.renrentui.renrenentity.domain.TemCorModel;
import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenentity.TemplateDetail;
@Repository
public class TemplateDetailDao extends DaoBase implements ITemplateDetailDao {

	@Override
	public int deleteByTemplateId(Long templateId) {
		return getMasterSqlSessionUtil().delete(
				"ITemplateDetailDao.deleteByTemplateId", templateId);
	}
	/**
	 * 插入一个模板详情
	 * 茹化肖
	 * 2015年11月16日15:57:40
	 */
	@Override
	public int insert(TemplateDetail record) {
		return getMasterSqlSessionUtil().insert(
				"ITemplateDetailDao.insert", record);
	}

	@Override
	public int insertList(List<TemplateDetail> recordList) {
		return getMasterSqlSessionUtil().insert(
				"ITemplateDetailDao.insertList", recordList);
	}
	/**
	 * ��ȡ��ͬ����ϸ�ؼ���Ϣ
	 * �㻯Ф
	 * 2015��9��29��13:39:58
	 * 
	 */
	@Override
	public ArrayList<ControlInfo> getTemplateList(Long templateId,Long orderId) {
		String statement = "ITemplateDetailDao.getTemplateList";
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("templateId", templateId);
		map.put("orderId", orderId);
		ArrayList<ControlInfo> res = (ArrayList) getReadOnlySqlSessionUtil().selectList(statement, map);
		return res;
	}

	@Override
	public List<TemplateDetail> listByTemplateId(Long templateId) {
		return getReadOnlySqlSessionUtil().selectList(
				"ITemplateDetailDao.listByTemplateId", templateId);

	}
	/**
	 * 获取模板控件信息
	 * 茹化肖
	 * @param taskId
	 * @return
	 */
	@Override
	public List<TemCorModel> getTemCorModelsByTaskId(Long taskId){
		return getReadOnlySqlSessionUtil().selectList(
				"ITemplateDetailDao.getTemCorModelsByTaskId", taskId);
	}

}
