package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.ITaskMsgDao;
import com.renrentui.renrenapi.service.inter.ITaskMsgService;
import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.req.TaskMsgReq;
import com.renrentui.renrenentity.req.TaskMsgUpdateReq;

@Service
public class TaskMsgService implements ITaskMsgService {
	@Autowired
	private ITaskMsgDao taskMsgDao;

	@Override
	public List<TaskMsg> getMyMsgList(TaskMsgReq req) {
		return taskMsgDao.getMyMsgList(req);
	}

	@Override
	public int updateMsg(TaskMsgUpdateReq req) {
		return taskMsgDao.updateMsg(req);
	}

	@Override
	public int insertMsg(TaskMsg req) {
		return taskMsgDao.insert(req);
	}

	@Override
	public Integer getMyMsgCount(Integer userId) {
		return taskMsgDao.getMyMsgCount(userId);
	}
}
