package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITaskDatumDao;
import com.renrentui.renrenentity.domain.TaskDatum;
import com.renrentui.renrenentity.domain.TaskDatumChild;
@Repository
public class TaskDatumDao extends DaoBase implements ITaskDatumDao{
	/**
	 * 插入用户提交的资料
	 * 2015年11月19日23:26:55
	 * 茹化肖
	 * 2015年11月19日23:27:04
	 * @param par
	 * @return
	 */
	@Override
	public int insertTaskDatum(TaskDatum par) {
		return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.ITaskDatumDao.insertTaskDatum", par);
	}

	@Override
	public int insertTaskDatumChild(TaskDatumChild child) {
		return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.ITaskDatumDao.insertTaskDatumChild", child);
	}

}
