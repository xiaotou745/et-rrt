package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao;
import com.renrentui.renrenentity.TaskCityRelation;

@Repository
public class TaskCityRelationDao extends DaoBase implements
		ITaskCityRelationDao {

	@Override
	public int insertList(List<TaskCityRelation> recordList) {
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao.insertList",
						recordList);
	}

	@Override
	public List<TaskCityRelation> selectByTaskId(Long taskId) {
		return getMasterSqlSessionUtil()
				.selectList("com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao.selectByTaskId",
						taskId);
	}
}
