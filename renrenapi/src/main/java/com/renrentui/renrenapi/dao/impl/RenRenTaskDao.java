package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenentity.RenRenTask;
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
	
}
