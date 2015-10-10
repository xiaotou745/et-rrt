package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;

@Repository
public class RenRenTaskDao extends DaoBase implements IRenRenTaskDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(RenRenTask record) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.insert",
				record);
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
	 * 获取任务详情 茹化肖 2015年9月29日13:13:43
	 */
	@Override
	public TaskDetail getTaskDetail(TaskDetailReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getTaskDetail";
		TaskDetail res = getReadOnlySqlSessionUtil().selectOne(statement, req);
		return res;
	}

	/**
	 * 验证任务是否可以领取(主库查询不加锁) 2015年9月29日17:16:32 茹化肖
	 */
	@Override
	public CheckTask checkTask(TaskDetailReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.checkTask";
		CheckTask res = getMasterSqlSessionUtil().selectOne(statement, req);
		return res;
	}

	/**
	 * 领取任务 减去任务剩余量
	 */
	@Override
	public int cutTaskAvailableCount(Long taskID) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.cutTaskAvailableCount";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskid", taskID);
		int res = getMasterSqlSessionUtil().update(statement, map);
		return res;
	}

	/**
	 * 取消任务 增加任务剩余量
	 */
	@Override
	public int addTaskAvailableCount(Long taskID) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.addTaskAvailableCount";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskid", taskID);
		int res = getMasterSqlSessionUtil().update(statement, map);
		return res;
	}

	@Override
	public PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(
			PagedRenRenTaskReq req) {
		return getReadOnlySqlSessionUtil()
				.selectPageList(
						"com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getPagedRenRenTaskList",
						req);
	}

	@Override
	public int setTaskStatus(long taskID, int status,String userName) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskID", taskID);
		map.put("status", status);
		map.put("userName", userName);
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.setTaskStatus",
						map);
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

	@Override
	public List<TaskModel> getMyReceivedTaskList(TaskReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getMyReceivedTaskList";
		List<TaskModel> taskModels = getMasterSqlSessionUtil().selectList(statement, req);
		return taskModels;
	}

	@Override
	public int getMyReceivedTaskListTotal(TaskReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getMyReceivedTaskListTotal";
		int taskTotal = getMasterSqlSessionUtil().selectOne(statement, req);
		return taskTotal;
	}

	@Override
	public List<TaskModel> getSubmittedTaskList(TaskReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getSubmittedTaskList";
		List<TaskModel> taskModels = getMasterSqlSessionUtil().selectList(statement, req);
		return taskModels;
	}

	@Override
	public int getSubmittedTaskListTotal(TaskReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.getSubmittedTaskListTotal";
		int taskTotal = getMasterSqlSessionUtil().selectOne(statement, req);
		return taskTotal;
	}
	
	/**
	 * 超时取消任务服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	@Override
	public void outTimeCanelTask(){
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.outTimeCanelTask";
		int count= getMasterSqlSessionUtil().update(statement);
	}
}
