package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITaskSetpDao;
import com.renrentui.renrenentity.domain.TaskSetp;
@Repository
public class TaskSetpDao extends DaoBase implements ITaskSetpDao {
	
	/**
	 * 获取任务步骤信息(接口用)
	 * 2015年11月19日11:33:40
	 * 茹化肖
	 */
	@Override
	public List<TaskSetp> getSetpsByTaskId(Long taskID) {
		return getReadOnlySqlSessionUtil().selectList("com.renrentui.renrenapi.dao.inter.ITaskSetpDao.getSetpsByTaskId",taskID);
	}

}
