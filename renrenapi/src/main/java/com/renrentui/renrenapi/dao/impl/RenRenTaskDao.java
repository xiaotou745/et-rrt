package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.TaskDetailReq;
@Repository
public class RenRenTaskDao extends DaoBase implements IRenRenTaskDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(RenRenTask record) {
return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.insert", record);
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
	/**
	 * 领取任务 减去任务剩余量
	 */
	@Override
	public int cutTaskAvailableCount(Long taskID) {
		String statement = "com.renrentui.renrenapi.dao.inter.IRenRenTaskDao.cutTaskAvailableCount";
		HashMap<String, Object> map=new HashMap<String, Object> ();
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
		HashMap<String, Object> map=new HashMap<String, Object> ();
		map.put("taskid", taskID);
		int res = getMasterSqlSessionUtil().update(statement, map);
		return res;
	}
	
	
}
