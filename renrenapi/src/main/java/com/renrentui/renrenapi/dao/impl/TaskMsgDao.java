package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITaskMsgDao;
import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.req.TaskMsgReq;
import com.renrentui.renrenentity.req.TaskMsgUpdateReq;

@Repository
public class TaskMsgDao extends DaoBase implements ITaskMsgDao {

	@Override
	public List<TaskMsg> getMyMsgList(TaskMsgReq req) {
		return getReadOnlySqlSessionUtil().selectList(
				"ITaskMsgDao.getMyMsgList", req);
	}

	@Override
	public int updateMsg(TaskMsgUpdateReq req) {
		return getMasterSqlSessionUtil().update("ITaskMsgDao.updateMsg", req);
	}

	@Override
	public int insert(TaskMsg record) {
		return getMasterSqlSessionUtil().insert("ITaskMsgDao.insert", record);
	}

	/**
	 * 批量新增消息 茹化肖 2015年11月30日14:42:17
	 */
	@Override
	public int insertList(List<TaskMsg> msgList) {
		return getMasterSqlSessionUtil().insert("ITaskMsgDao.insertList",
				msgList);
	}

	@Override
	public Integer getMyMsgCount(Integer userId) {
		return getReadOnlySqlSessionUtil().selectOne("ITaskMsgDao.getMyMsgCount", userId);
	}

}
