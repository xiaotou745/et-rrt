package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;
@Repository
public class RenRenTaskDao extends DaoBase implements IRenRenTaskDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(RenRenTask record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(RenRenTask record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RenRenTask selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(RenRenTask record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(RenRenTask record) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 获取任务详情 
	 * 茹化肖
	 * 2015年9月29日13:13:43
	 */
	@Override
	public TaskDetail getTaskDetail(TaskDetailReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getTaskDetail";
		TaskDetail res = getReadOnlySqlSessionUtil().selectOne(statement, req);
		return res;
	}
	/**
	 * 验证任务是否可以领取(主库查询不加锁)
	 * 2015年9月29日17:16:32
	 * 茹化肖
	 */
	@Override
	public CheckTask checkTask(TaskDetailReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.checkTask";
		CheckTask res = getMasterSqlSessionUtil().selectOne(statement, req);
		return res;
	}

	@Override
	public List<TaskModel> getNewTaskList(TaskReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getNewTaskList";
		List<TaskModel> taskModels = getMasterSqlSessionUtil().selectList(statement, req);
		return taskModels;
	}

	@Override
	public int getNewTaskTotal(TaskReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getNewTaskListTotal";
		int taskTotal = getMasterSqlSessionUtil().selectOne(statement, req);
		return taskTotal;
	}
	
}
