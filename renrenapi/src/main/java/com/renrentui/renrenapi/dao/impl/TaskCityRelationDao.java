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
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertList(List<TaskCityRelation> recordList) {
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao.insertList",
						recordList);
	}

	@Override
	public int insertSelective(TaskCityRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TaskCityRelation selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TaskCityRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TaskCityRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
