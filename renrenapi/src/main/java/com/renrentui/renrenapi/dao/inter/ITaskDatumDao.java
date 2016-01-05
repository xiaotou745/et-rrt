package com.renrentui.renrenapi.dao.inter;

import java.util.List;
import java.util.Map;

import com.renrentui.renrenentity.domain.TaskDatum;
import com.renrentui.renrenentity.domain.TaskDatumChild;
import com.renrentui.renrenentity.domain.TaskDatumDetail;
import com.renrentui.renrenentity.domain.TaskDatumDetailGroup;
import com.renrentui.renrenentity.domain.TaskDatumGroup;
import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.domain.TaskDatumTitle;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
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
	 * 获取我的资料列表表头上的总数
	 * @author hailongzhao
	 * @date 20151202
	 * @param req
	 * @return
	 */
	 List<Map<String,Integer>> getMyTaskDatumListTotal(TaskDatumReq req);
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
     
 	/**
 	 * 获取资料模板或资料详情中的分组信息
 	 * @param req
 	 * @author hailongzhao
 	 * @date 20151126
 	 * @return
 	 */
 	 List<TaskDatumDetailGroup> getTaskDatumGroupList(TaskDatumDetailReq req);
 	 
  	/**
  	 * 获取资料模板或资料详情中的分组中的控件值
  	 * @param req
  	 * @author hailongzhao
  	 * @date 20151126
  	 * @return
  	 */
  	 List<TaskDatumDetail> getTaskDatumDetailList(TaskDatumDetailReq req);
  	 
		/**
		 * 获取我的资料分组后列表
		 * @author hailongzhao
		 * @date 20151230
		 * @param req
		 * @return
		 */
	List<TaskDatumGroup> getMyTaskDatumGroupList(TaskDatumReq req);
}
