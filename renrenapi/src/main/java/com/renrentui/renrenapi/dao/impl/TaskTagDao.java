package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITaskTagDao;
import com.renrentui.renrenentity.TaskTag;
@Repository
public class TaskTagDao extends DaoBase implements ITaskTagDao{

	@Override
	public List<TaskTag> getAll() {
return getReadOnlySqlSessionUtil().selectList("ITaskTagDao.getAll");
	}

	@Override
	public int update(TaskTag record) {
return getMasterSqlSessionUtil().update("ITaskTagDao.update", record);
	}

	@Override
	public int insert(TaskTag record) {
return getMasterSqlSessionUtil().insert("ITaskTagDao.insert", record);
	}

}
