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
		return getReadOnlySqlSessionUtil().selectList("ITaskSetpDao.getSetpsByTaskId",taskID);
	}

	/**
	 * 查询某一个文章对应的任务数量
	 * @author CaoHeYang
	 * @date  20160125
	 * @param articleId
	 * @return
	 */
	@Override
	public int selectCountByArticle(long articleId) {
		return getReadOnlySqlSessionUtil().selectOne("ITaskSetpDao.selectCountByArticle",articleId);
	}

}
