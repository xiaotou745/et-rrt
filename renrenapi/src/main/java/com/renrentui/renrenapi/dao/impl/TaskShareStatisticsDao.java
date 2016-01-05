package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITaskShareStatisticsDao;
import com.renrentui.renrenentity.TaskShareStatistics;

@Repository
public class TaskShareStatisticsDao extends DaoBase implements ITaskShareStatisticsDao{

	@Override
	public int insert(TaskShareStatistics record) {
		return getMasterSqlSessionUtil().insert("ITaskShareStatisticsDao.insert", record);
	}

}
