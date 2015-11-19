package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.domain.TaskSetp;

public interface ITaskSetpDao {

	/**
	 * 获取任务步骤信息
	 * 茹化肖
	 * 2015年11月19日11:33:18
	 * @param taskID
	 * @return
	 */
	List<TaskSetp> getSetpsByTaskId(Long taskID);
}
