package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITaskMsgDao;
import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.req.TaskMsgReq;
import com.renrentui.renrenentity.req.TaskMsgUpdateReq;
@Repository
public class TaskMsgDao extends DaoBase implements ITaskMsgDao{

	@Override
	public List<TaskMsg> getMyMsgList(TaskMsgReq req) {
return getReadOnlySqlSessionUtil().selectList("com.renrentui.renrenapi.dao.inter.ITaskMsgDao.getMyMsgList", req);
	}

	@Override
	public int updateMsg(TaskMsgUpdateReq req) {
		return getMasterSqlSessionUtil().update("com.renrentui.renrenapi.dao.inter.ITaskMsgDao.updateMsg", req);
	}

	@Override
	public int insert(TaskMsg record) {
return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.ITaskMsgDao.insert", record);
	}

}
