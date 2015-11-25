package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.domain.TaskDatum;
import com.renrentui.renrenentity.domain.TaskDatumChild;
import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.domain.TaskDatumTitle;
import com.renrentui.renrenentity.req.TaskDatumReq;

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
	
	/**
	 * 获取我的资料列表
	 * @author hailongzhao
	 * @date 20151125
	 * @param req
	 * @return
	 */
	 List<TaskDatumModel> getMyTaskDatumList(TaskDatumReq req);
		/**
		 * 获取我的资料列表中每个资料的title
		 * @author hailongzhao
		 * @date 20151125
		 * @param req
		 * @return
		 */
     List<TaskDatumTitle> getMyTaskDatumTitleList(TaskDatumReq req);
}
