package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckSubmitTask;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.ClienterTask;
import com.renrentui.renrenentity.domain.MyReceiveTask;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.domain.ReceiveNum;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.domain.TaskPartnerItem;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemplateGroup;
import com.renrentui.renrenentity.req.PagedPartnerReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
import com.renrentui.renrenentity.req.TaskReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;

@Repository
public class RenRenTaskDao extends DaoBase implements IRenRenTaskDao {

	@Override
	public int insert(RenRenTask record) {
		return getMasterSqlSessionUtil().insert(
				"IRenRenTaskDao.insert",
				record);
	}

	@Override
	public RenRenTask selectById(Long id) {
		return getMasterSqlSessionUtil().selectOne(
				"IRenRenTaskDao.selectById",
				id);
	}


	/**
	 * 获取任务详情 茹化肖 2015年9月29日13:13:43
	 * 修改时间 2015年11月19日11:21:23
	 * 修改人 
	 */
	@Override
	public RenRenTask getTaskDetail(TaskDatumDetailReq req) {
		String statement = "IRenRenTaskDao.getTaskDetail";
		RenRenTask res = getReadOnlySqlSessionUtil().selectOne(statement, req);
		return res;
	}

	/**
	 * 验证任务是否可以领取(主库查询不加锁) 2015年9月29日17:16:32 茹化肖
	 */
	@Override
	public CheckTask checkTask(TaskDatumDetailReq req) {
		String statement = "IRenRenTaskDao.checkTask";
		CheckTask res = getMasterSqlSessionUtil().selectOne(statement, req);
		return res;
	}

	/**
	 * 领取任务 减去任务剩余量
	 */
	@Override
	public int cutTaskAvailableCount(Long taskID) {
		String statement = "IRenRenTaskDao.cutTaskAvailableCount";
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
		String statement = "IRenRenTaskDao.addTaskAvailableCount";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taskid", taskID);
		int res = getMasterSqlSessionUtil().update(statement, map);
		return res;
	}

	/**
	 *  审核任务 列表  数据
	 */
	@Override
	public PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(
			PagedRenRenTaskReq req) {
		return getReadOnlySqlSessionUtil()
				.selectPageList(
						"IRenRenTaskDao.getPagedRenRenTaskList",
						req);
	}

	@Override
	public int setTaskStatus(UpdateStatusReq req) {
		return getMasterSqlSessionUtil()
				.update("IRenRenTaskDao.setTaskStatus",
						req);
	}
	@Override
	public List<TaskModel> getNewTaskList(TaskReq req) {
		String statement = "IRenRenTaskDao.getNewTaskList";
		List<TaskModel> taskModels = getMasterSqlSessionUtil().selectList(statement, req);
		return taskModels;
	}

	@Override
	public int getNewTaskTotal(TaskReq req) {
		String statement = "IRenRenTaskDao.getNewTaskListTotal";
		int taskTotal = getMasterSqlSessionUtil().selectOne(statement, req);
		return taskTotal;
	}

	@Override
	public List<MyReceiveTask> getMyReceivedTaskList(TaskReq req) {
		String statement = "IRenRenTaskDao.getMyReceivedTaskList";
		List<MyReceiveTask> taskModels = getMasterSqlSessionUtil().selectList(statement, req);
		return taskModels;
	}
	/**
	 * 查询已接任务各种数量
	 * 茹化肖 修改
	 */
	@Override
	public ReceiveNum  getMyReceivedTaskListTotal(TaskReq req) {
		String statement = "IRenRenTaskDao.getMyReceivedTaskListTotal";
		return getMasterSqlSessionUtil().selectOne(statement, req);
	}


	
	/**
	 * 超时取消任务服务
	 * 服务.茹化肖修改
	 * @author CaoHeYang
	 * @date 20151009
	 */
	@Override
	public void outTimeCanelTask(){
		String statement = "IRenRenTaskDao.outTimeCanelTask";
		int count= getMasterSqlSessionUtil().update(statement);
	}

	@Override
	public int update(RenRenTask record) {
		return getMasterSqlSessionUtil().update(
				"IRenRenTaskDao.update",
				record);
	}

	@Override
	public List<RenRenTask> getListByTemplateId(Long templateId) {
		return getReadOnlySqlSessionUtil().selectList(
				"IRenRenTaskDao.getListByTemplateId",
				templateId);
	}
	/**
	 * 插入步骤信息
	 * 茹化肖
	 * 2015年11月16日15:27:40
	 */
	@Override
	public int insertTaskSetp(TaskSetp setp) {
		return getMasterSqlSessionUtil().insert("IRenRenTaskDao.insertTaskSetp", setp);
	}
	/**
	 * 插入模板组信息
	 * 茹化肖
	 * 2015年11月16日15:43:29
	 */
	@Override
	public int insertTemplateGrpup(TemplateGroup group) {
		return getMasterSqlSessionUtil().insert("IRenRenTaskDao.insertTemplateGrpup", group);
	}
	/**
	 * 插入领取任务关系数据
	 * 2015年11月19日14:29:56
	 * 茹化肖
	 * V1.0.2
	 */
	@Override
	public Long insertClienterTask(ClienterTask task) {
		return getMasterSqlSessionUtil().selectOne("IRenRenTaskDao.insertClienterTask", task);
	}
	/**
	 * 提交一次资料就讲完成次数加1
	 * 2015年11月20日00:14:19
	 * 茹化肖
	 */
	@Override
	public int addClienterCompleteCount(Long ctId) {
		return getMasterSqlSessionUtil().update("IRenRenTaskDao.addClienterCompleteCount", ctId);
	}
	/**
	 * 清除任务下的步骤,控件,控件组,投放城市
	 * 茹化肖
	 * 2015年11月26日16:58:47
	 */
	@Override
	public int clearTaskInfo(Long taskId) {
		return getMasterSqlSessionUtil().delete("IRenRenTaskDao.clearTaskInfo", taskId);
	}
	/**
	 * 修改任务更新任务信息
	 * 茹化肖
	 * 2015年11月26日16:59:57
	 */
	@Override
	public int updateTaskInfo(RenRenTask task) {
		return getMasterSqlSessionUtil().delete("IRenRenTaskDao.updateTaskInfo", task);
	}
	/**
	 * 获取任务下面所有骑士ID
	 */
	@Override
	public List<Long> getClinerIdList(Long taskId) {
		return getMasterSqlSessionUtil().selectList("IRenRenTaskDao.getClinerIdList", taskId);
	}
	/**
	 * 服务.获取还有一天过期任务
	 * 茹化肖
	 * 2015年12月15日13:02:19
	 * 
	 * @return
	 */
	@Override
	public List<RenRenTask> getTaskSendList() {
		return getMasterSqlSessionUtil().selectList("IRenRenTaskDao.getTaskSendList");
	}
	/**
	 * 服务.设置任务为已经发送过站内信
	 * @param taskId
	 * @return
	 */
	@Override
	public int setTaskSend(Long taskId) {
		return getMasterSqlSessionUtil().update("IRenRenTaskDao.setTaskSend", taskId);
	}

	@Override
	public int updatePartnerNum(Long taskId) {
		return getMasterSqlSessionUtil().update("IRenRenTaskDao.updatePartnerNum", taskId);
	}
	@Override
	public CheckSubmitTask checkOrderSubmit(SubmitTaskReq req) {
		return getMasterSqlSessionUtil().selectOne("IRenRenTaskDao.checkOrderSubmit", req);
	}
	/**
	 * 
	 * 
	 * 任务下所有数据导出
	 */
	@Override
	public List<OrderAudit> taskDaumExport(Long taskId) {
		return getReadOnlySqlSessionUtil().selectList("IRenRenTaskDao.taskDaumExport", taskId);
	}

	@Override
	public PagedResponse<TaskPartnerItem> getPagedTaskPartnerList(
			PagedPartnerReq req) {
		return getMasterSqlSessionUtil().selectPageList("IRenRenTaskDao.getPagedTaskPartnerList", req);
	}
}
