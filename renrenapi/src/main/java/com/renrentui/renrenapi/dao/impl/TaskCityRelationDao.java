package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.TaskCityRelation;

@Repository
public class TaskCityRelationDao extends DaoBase implements
		ITaskCityRelationDao {

	@Override
	public int insertList(List<TaskCityRelation> recordList) {
		return getMasterSqlSessionUtil()
				.update("ITaskCityRelationDao.insertList",
						recordList);
	}

	@Override
	public List<TaskCityRelation> selectByTaskId(Long taskId) {
		return getMasterSqlSessionUtil()
				.selectList("ITaskCityRelationDao.selectByTaskId",
						taskId);
	}

	@Override
	public int deleteByTaskId(Long taskId) {
		return getMasterSqlSessionUtil()
				.delete("ITaskCityRelationDao.deleteByTaskId",
						taskId);
	}
	/**
	 * 获取任务投放区域
	 */
	@Override
	public PublicProvinceCity getTaskCity(Long taskId) {
		return getReadOnlySqlSessionUtil().selectOne("ITaskCityRelationDao.getTaskCity", taskId);
	}
}
