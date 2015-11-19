package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.domain.TaskDatum;
import com.renrentui.renrenentity.domain.TaskDatumChild;

public interface ITaskDatumDao {
	/**
	 * 插入用户提交的资料
	 * 2015年11月19日23:26:55
	 * 茹化肖
	 * 2015年11月19日23:27:04
	 * @param par
	 * @return
	 */
	int insertTaskDatum(TaskDatum par);
	/**
	 * 插入任务资料详细数据
	 * @param child
	 * @return
	 */
	int insertTaskDatumChild(TaskDatumChild child);

}
