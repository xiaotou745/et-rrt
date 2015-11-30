package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.renrentui.renrenapi.common.TransactionalRuntimeException;
import com.renrentui.renrenapi.dao.impl.TaskMsgDao;
import com.renrentui.renrenapi.dao.inter.IAttachmentDao;
import com.renrentui.renrenapi.dao.inter.IBusinessBalanceDao;
import com.renrentui.renrenapi.dao.inter.IBusinessBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IBusinessDao;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IOrderChildDao;
import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenapi.dao.inter.IOrderLogDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskLogDao;
import com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao;
import com.renrentui.renrenapi.dao.inter.ITaskDatumDao;
import com.renrentui.renrenapi.dao.inter.ITaskMsgDao;
import com.renrentui.renrenapi.dao.inter.ITaskSetpDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailDao;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailSnapshotDao;
import com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrencore.enums.BBalanceRecordType;
import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrencore.enums.DatumAuditStatus;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.enums.PaymentMethodType;
import com.renrentui.renrencore.enums.SubmitTaskCode;
import com.renrentui.renrencore.enums.TaskOpType;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.enums.TaskType;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.BusinessBalanceRecord;
import com.renrentui.renrenentity.ClienterLog;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.OrderChild;
import com.renrentui.renrenentity.OrderLog;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.RenRenTaskLog;
import com.renrentui.renrenentity.TaskCityRelation;
import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.TemplateDetail;
import com.renrentui.renrenentity.TemplateSnapshot;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.domain.CheckSubmitTask;
import com.renrentui.renrenentity.domain.ClienterTask;
import com.renrentui.renrenentity.domain.MyReceiveTask;
import com.renrentui.renrenentity.domain.OrderRetrunModel;
import com.renrentui.renrenentity.domain.RenRenTaskDetail;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskDatum;
import com.renrentui.renrenentity.domain.TaskDatumChild;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemCorModel;
import com.renrentui.renrenentity.domain.TemplateGroup;
import com.renrentui.renrenentity.req.BusinessBalanceReq;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.SaveTaskReq;
import com.renrentui.renrenentity.req.SubmitTaskReq;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
import com.renrentui.renrenentity.req.TaskReq;
import com.renrentui.renrenentity.req.TemplateSnapshotReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;

@Service
public class RenRenTaskService implements IRenRenTaskService {
	@Autowired
	private IRenRenTaskDao renRenTaskDao;
	@Autowired
	private ITemplateDao templateDao;
	@Autowired
	private ITemplateDetailDao templateDetailDao;
	@Autowired
	private ITemplateSnapshotDao templateSnapshotDao;
	@Autowired
	private ITemplateDetailSnapshotDao templateDetailSnapshotDao;
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IClienterLogDao clienterLogDao;
	@Autowired
	private IOrderLogDao orderLogDao;

	@Autowired
	private IOrderChildDao orderChildDaoDao;
	@Autowired
	private ITaskCityRelationDao taskCityRelationDao;
	@Autowired
	private IPublicProvinceCityService publicProvinceCityService;

	@Autowired
	private IAttachmentDao attachmentDao;

	@Autowired
	private IRenRenTaskLogDao renRenTaskLogDao;
	@Autowired
	private IBusinessDao businessDao;
	@Autowired
	private IBusinessBalanceDao businessBalanceDao;
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;
	
	@Autowired
	private ITaskSetpDao taskSetpDao;
	
	@Autowired
	private ITaskDatumDao taskDatumDao;
	@Autowired
	private ITaskMsgDao taskMsgDao;

	/**
	 * 获取任务详情 茹化肖 2015年9月29日13:00:35
	 * 修改时间 2015年11月19日11:20:38
	 * 修改人  
	 */
	@Override
	public TaskDetail getTaskDetail(TaskDatumDetailReq req) {

		TaskDetail detail = new TaskDetail();
	 	RenRenTask task= renRenTaskDao.getTaskDetail(req);//获取任务 任务信息
	 	//获取任务步骤 等信息
	 	ArrayList<TaskSetp> taskSetps=(ArrayList<TaskSetp>)taskSetpDao.getSetpsByTaskId(req.getTaskId());
	 	detail.setTask(task);
	 	detail.setTaskSetps(taskSetps);
		return detail;
	}

	/**
	 * 领取任务接口 茹化肖 2015年9月29日16:30:00
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public OrderRetrunModel getTask(TaskDatumDetailReq req) {
		OrderRetrunModel model = new OrderRetrunModel();
		//获取任务相关数据
		CheckTask detail = renRenTaskDao.checkTask(req);
		if (detail == null)// 没有查询到任务相关信息
		{
			model.setCode(GetTaskCode.TaskExpire);
			return model;
		}
		//插入关系数据
		ClienterTask cTask=new ClienterTask();
		cTask.setAmount(detail.getAmount());
		cTask.setBusinessId(detail.getBusinessId());
		cTask.setClienterId(req.getUserId());
		cTask.setTaskId(req.getTaskId());
		cTask.setTaskType(detail.getTaskType());
		int res =renRenTaskDao.insertClienterTask(cTask);
		if(res==1)//成功插入
		{
			model.setCode(GetTaskCode.Success);
		}
		else {//该任务已经存在
			model.setCode(GetTaskCode.TaskHad);
		}
		return model;
//		// 领取任务 插入订单
//		String orderNoString = OrderNoHelper.generateOrderCode(req.getUserId());// 生成订单号
//		Order order = new Order();
//		order.setOrderNo(orderNoString);
//		order.setClienterId(req.getUserId());
//		order.setTaskId(req.getTaskId());
//		order.setAmount(detail.getAmount());
//		Date dealLineDate = ParseHelper.plusDate(new Date(), 3,
//				(int) detail.getTaskCycle());
//		System.out.println(dealLineDate);
//		order.setDeadlineTime(dealLineDate);
//		int res = orderDao.addOrder(order);// 添加订单信息
//		int rescut = renRenTaskDao.cutTaskAvailableCount(req.getTaskId());// 扣减任务量
//
//		ClienterLog log = new ClienterLog();
//		log.setClienterId(req.getUserId());
//		log.setOptName("地推员ID");
//		log.setRemark("地推员:" + req.getUserId() + "领取任务:" + req.getTaskId()
//				+ "订单号:" + orderNoString);
//		int reslog = clienterLogDao.addClienterLog(log);// 记录C端日志
//
//		OrderLog orderLog = new OrderLog();
//		orderLog.setOrderNo(orderNoString);
//		orderLog.setOrderId(order.getId());
//		orderLog.setOptType(Short.valueOf("1"));
//		orderLog.setOptName("地推员:" + req.getUserId());
//		orderLog.setRemark("地推员:" + req.getUserId() + "抢单:" + orderNoString);
//		int orderlogres = orderLogDao.addOrderLog(orderLog);// 记录订单操作日志
//		if (res > 0 && rescut > 0 && reslog > 0 && orderlogres > 0) {
//			model.setOrderId(order.getId());
//			model.setCode(GetTaskCode.Success);
//			return model;
//		} else {
//			Error error = new Error("添加订单错误");
//			throw new RuntimeErrorException(error);
//		}
	}

	/**
	 * 取消任务 茹化肖 2015年9月30日13:24:27
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public CancelTaskCode cancelTask(CancelTaskReq req) {
		// 验证订单相关信息
		CheckCancelOrder check = orderDao.checkCancelOrder(req);
		if (check == null)// 订单不存在
			return CancelTaskCode.OrderNull;
		// if (check.getCancelCan() == 0)// 订单不能取消 //窦海超 去掉了判断订单进行中-未完成不能取消状态
		// {
		if (check.getIsCancle() == 1)// 订单已经取消
			return CancelTaskCode.TaskIsCancel;
		// if (check.getIsComplete() == 1)// 订单已经完成不能取消
		// return CancelTaskCode.TaskComplete;
		// return CancelTaskCode.CantCancel;// 订单不可取消
		// }

		int res = orderDao.cancelOrder(req);// 取消订单

		OrderLog orderLog = new OrderLog();
		orderLog.setOrderNo(check.getOrderNo());
		orderLog.setOrderId(req.getOrderId());
		orderLog.setOptType(Short.valueOf("1"));
		orderLog.setOptName("地推员:" + req.getUserId());
		orderLog.setRemark("地推员:" + req.getUserId() + "取消订单:"
				+ check.getOrderNo());
		int orderlogres = orderLogDao.addOrderLog(orderLog);// 记录订单操作日志

		int addres = renRenTaskDao.addTaskAvailableCount(check.getTaskId());// 增加任务的剩余量
		if (res > 0 && orderlogres > 0 && addres > 0) {
			return CancelTaskCode.Success;
		} else {
			Error error = new Error("取消任务错误");
			throw new RuntimeErrorException(error);
		}
	}

	/**
	 * 提交任务 茹化肖 2015年10月8日13:37:08
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public SubmitTaskCode submitTask(SubmitTaskReq req) {
		//1.插入TaskDatum 任务资料表
		TaskDatum taskDatum=new TaskDatum();
		taskDatum.setCtId(req.getCtId());
		taskDatum.setClienterId(req.getUserId());
		taskDatum.setTaskId(req.getTaskId());
		taskDatumDao.insertTaskDatum(taskDatum);
		//2.插入合同详细数据
		for (int i = 0; i < req.getValueInfo().size(); i++) {
			TaskDatumChild child=new TaskDatumChild();
			child.setTaskDatumId(taskDatum.getId());
			child.setControlName(req.getValueInfo().get(i).getControlName());
			child.setControlValue(req.getValueInfo().get(i).getControlValue());
			taskDatumDao.insertTaskDatumChild(child);
		}
		renRenTaskDao.addClienterCompleteCount(req.getCtId());
		return SubmitTaskCode.Success;
		
		
//		CheckSubmitTask check = orderDao.checkOrderSubmit(req);
//		int deleInt = 0;
//		if (check == null)
//			return SubmitTaskCode.CantSubmit;
//		if (check.getSubmitCan() == 0)// 订单不可提交
//		{
//			if (check.getIsCancel() == 1)// 订单已经取消
//				return SubmitTaskCode.OrderCancel;
//			if (check.getTaskClosed() == 1)// 任务已关闭 且不是修改后提交
//				return SubmitTaskCode.TaskClosed;
//			if (check.getReSubmit() == 1)// 任务已经提交待审核 不可重复提交
//				return SubmitTaskCode.ReSubmit;
//			return SubmitTaskCode.CantSubmit;
//		}
//		OrderLog orderLog = new OrderLog();
//		orderLog.setOrderNo("");
//		orderLog.setOrderId(req.getOrderId());
//		orderLog.setOptType(Short.valueOf("2"));
//		orderLog.setOptName("地推员:" + req.getUserId());
//		orderLog.setRemark("地推员:" + req.getUserId() + "提交订单ID:"
//				+ req.getOrderId());
//		if (check.getSubmitCan() == 1 && check.getIsAgainSubmit() == 1) {
//			// 再次提交合同信息..删除之前提交的合同
//			deleInt = orderChildDaoDao.deleteOrderChild(req.getOrderId());
//			if (deleInt <= 0) {
//				// 清除旧数据失败
//				Error error = new Error("清除旧合同信息错误");
//				throw new RuntimeErrorException(error);
//			}
//			orderLog.setOptType(Short.valueOf("3"));
//			orderLog.setRemark("地推员:" + req.getUserId() + "审核拒绝后再次提交订单ID:"
//					+ req.getOrderId());
//		}
//		// 更新订单状态00
//		int resSubmit = orderDao.submitOrder(req);
//		// 插入订单操作记录
//		int orderlogres = orderLogDao.addOrderLog(orderLog);// 记录订单操作日志
//		// 插入子订单信息
//		int childres = 0;
//		for (int i = 0; i < req.getValueInfo().size(); i++) {
//			OrderChild child = new OrderChild();
//			child.setOrderId(req.getOrderId());
//			child.setControlName(req.getValueInfo().get(i).getControlName());
//			child.setControlValue(req.getValueInfo().get(i).getControlValue());
//			child.setTemplateSnapshotId(req.getTemplateId());
//			childres += orderChildDaoDao.insert(child);
//		}
//		if (resSubmit > 0 && orderlogres > 0
//				&& (childres == req.getValueInfo().size())) {
//			// 返回订单提交成功
//			return SubmitTaskCode.Success;
//		} else {
//			// 提交合同失败
//			Error error = new Error("提交合同信息失败");
//			throw new RuntimeErrorException(error);
//		}
	}
	/**
	 * 保存任务
	 * 茹化肖
	 * 2015年11月16日11:36:42
	 * remark:新业务需求修改
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int insert(SaveTaskReq taskreq, List<Integer> regionCodes,List<Attachment> attachments) {
	    RenRenTask	task=taskreq.getRenRenTask();
	    //1.插入任务信息
	    if(task.getId()>0)
	    {
	    	//清除任务下的步骤,控件,控件组,投放城市
	    	renRenTaskDao.clearTaskInfo(task.getId());
	    	//更新任务信息
	    	renRenTaskDao.updateTaskInfo(task);
	    }
	    else {
	    	 //插入任务信息
	    	 int result = renRenTaskDao.insert(task);
		}
	   
	    //2. 插入步骤信息
	    ArrayList<TaskSetp> setplist=taskreq.getTaskSetps();
	    for (int i = 0; i < setplist.size(); i++) {
	    	TaskSetp setp=setplist.get(i);
	    	setp.setTaskId(task.getId());//设置任务ID
	    	renRenTaskDao.insertTaskSetp(setp);
		}
	    //3.插入模板组信息
	    ArrayList<TemplateGroup> groups=taskreq.getTemplateGroup();
	    for (int i = 0; i < groups.size(); i++) {
	    	TemplateGroup group=groups.get(i);
	    	group.setTaskId(task.getId());//设置任务ID
	    	renRenTaskDao.insertTemplateGrpup(group);
	    	//4.插入每个模板组下面的模板详情
	    	List<TemplateDetail> detaillist= group.getTemplateList();
	    	for (int j = 0; j < detaillist.size(); j++) {
	    		TemplateDetail detail=detaillist.get(j);
	    		detail.setTemplateGroupId(group.getId());
	    		templateDetailDao.insert(detail);
			}
		}
	    //4.任务投放区域
	    List<TaskCityRelation> recordList = getCityRelationList(task, regionCodes);
		int relationResult = taskCityRelationDao.insertList(recordList);
	    //5.记录任务操作日志
	    RenRenTaskLog logRecord = new RenRenTaskLog();
		logRecord.setRenrenTaskId(task.getId());
		logRecord.setOptName(task.getCreateName());
		logRecord.setOptType((short) TaskOpType.NewTask.value());
		logRecord.setRemark(TaskOpType.NewTask.desc());
		renRenTaskLogDao.insert(logRecord);
	    return 1;
	}

	private void updateBusinessBalance(Long taskId, Long businessId,
			Double totalFee, Double oldBalance, BBalanceRecordType recordType,
			String userName) {
		BusinessBalanceReq balanceReq = new BusinessBalanceReq();
		balanceReq.setBusinessId(businessId);
		balanceReq.setBalance(totalFee);
		int balanceResult = businessBalanceDao
				.updateBalanceByBusinessId(balanceReq);
		if (balanceResult == 0) {
			throw new TransactionalRuntimeException(recordType.desc() + "更新商户余额失败");
		}
		BusinessBalanceRecord balanceRecord = new BusinessBalanceRecord();
		balanceRecord.setBusinessId(businessId);
		balanceRecord.setAmount(totalFee);
		balanceRecord.setAfterAmount(oldBalance + totalFee);
		balanceRecord.setOptName(userName);
		balanceRecord.setOrderId(taskId);
		balanceRecord.setRelationNo(taskId.toString());
		balanceRecord.setRecordType((short) recordType.value());
		balanceRecord.setRemark(recordType.desc());
		businessBalanceRecordDao.insert(balanceRecord);
	}

	/**
	 * 审核任务 列表 数据
	 */
	@Override
	public PagedResponse<RenRenTaskModel> getPagedRenRenTaskList(
			PagedRenRenTaskReq req) {
		return renRenTaskDao.getPagedRenRenTaskList(req);
	}
	/**
	 * 任务列表(审核)
	 * 茹化肖
	 * 2015年11月27日10:10:34
	 * 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int setTaskStatus(UpdateStatusReq req) {
		TaskStatus status = TaskStatus.getEnum(req.getStatus());
		if (status == TaskStatus.WaitAudit || status == TaskStatus.Expired) {
			throw new TransactionalRuntimeException("不能手工将任务置为待审核或过期状态");
		}
		int result = renRenTaskDao.setTaskStatus(req);
		RenRenTaskLog logRecord = new RenRenTaskLog();
		logRecord.setRenrenTaskId(req.getReocrdId());
		logRecord.setOptName(req.getUserName());

		TaskOpType opType = TaskOpType.NewTask;
		if (status == TaskStatus.Audited) {
			opType = TaskOpType.Audited;
		} else if (status == TaskStatus.Reject) {
			opType = TaskOpType.Reject;
		} else if (status == TaskStatus.Stop) {
			opType = TaskOpType.Stop;
			inserMsgList(req.getReocrdId());//给所有骑士发站内信
			
			
		} else if (status == TaskStatus.Cancel) {
			opType = TaskOpType.CancelTask;
			//V1.0.2需求.暂时和商户没有线上结算
//			RenRenTask oldTaskModel = renRenTaskDao.selectById(req
//					.getReocrdId());
//			BusinessBalance oldBalance = businessBalanceDao
//					.selectByBusinessId(oldTaskModel.getBusinessId());
//			if (oldBalance == null) {
//				throw new TransactionalRuntimeException("没有找到id="
//						+ oldTaskModel.getBusinessId() + "的商户的余额信息");
//			}
//			// 取消任务时，给商户还钱
//			Double totalFee = oldTaskModel.getAmount()
//					* oldTaskModel.getTaskTotalCount();
//			updateBusinessBalance(req.getReocrdId(),
//					oldTaskModel.getBusinessId(), totalFee,
//					oldBalance.getBalance(), BBalanceRecordType.CancelTask,
//					req.getUserName());
		} else if (status == TaskStatus.HasSettlement) {
			opType = TaskOpType.SettlementTask;
		}
		
		logRecord.setOptType((short) opType.value());
		logRecord.setRemark(opType.desc());
		renRenTaskLogDao.insert(logRecord);
		return result;
	}
	/**
	 * 取消任务批量插入消息数据
	 * @param list 骑士ID
	 */
	private void inserMsgList(Long taskID)
	{
		RenRenTask task=renRenTaskDao.selectById(taskID);
		List<Long> list=renRenTaskDao.getClinerIdList(taskID);
		List<TaskMsg> msgList=new ArrayList<TaskMsg> ();
		for (int i = 0; i < list.size(); i++) {
			TaskMsg itemMsg=new TaskMsg();
			itemMsg.setClienterId(list.get(i));
			itemMsg.setTaskId(task.getId());
			itemMsg.setTitle("任务未过期但管理员手动终止");
			itemMsg.setCreateName("admin");
			itemMsg.setMsg("《"+task.getTaskTitle()+"》已经提前结束，您可以继续完成其他任务哦。如有疑问，请联系地推小管家：010-57173598");
			itemMsg.setMsgType(0);
			msgList.add(itemMsg);
			if(msgList.size()==50||i==list.size()-1)//集合满50个 或者已经没有批次 插入数据库
			{
				taskMsgDao.insertList(msgList);
				msgList.clear();//插完数据库清空集合
			}
		}
		
	}

	@Override
	public List<TaskModel> getNewTaskList(TaskReq req) {
		if (req.getCityCode()<=0||req.getProvinceCode()<=0||req.getUserId()<=0) {
			return null;
		}
		return renRenTaskDao.getNewTaskList(req);
	}

	@Override
	public int getNewTaskTotal(TaskReq req) {
		return renRenTaskDao.getNewTaskTotal(req);
	}
/**
 * 获取我的任务列表
 * @author hailongzhao
 * @date 20151124
 */
	@Override
	public List<MyReceiveTask> getMyReceivedTaskList(TaskReq req) {
		List<MyReceiveTask> result=new ArrayList<>();
		List<MyReceiveTask> taskList= renRenTaskDao.getMyReceivedTaskList(req);
		List<Long> taskIdList=taskList.stream().map(t->t.getTaskId()).distinct().collect(Collectors.toList());
		for (Long taskId : taskIdList) {
			List<MyReceiveTask> taskTempList=taskList.stream().filter(t->t.getTaskId()==taskId).collect(Collectors.toList());
			MyReceiveTask tempTask=taskTempList.get(0);
			for (MyReceiveTask myReceiveTask : taskTempList) {
				if (myReceiveTask.getTaskType()==TaskType.ContractTask.value()) {
					DatumAuditStatus datumAuditStatus=DatumAuditStatus.getEnum(myReceiveTask.getAuditStatus());
					if (datumAuditStatus!=null) {
						switch (datumAuditStatus) {
						case WaitAudit:
							tempTask.setAuditWaitNum(myReceiveTask.getAuditNum());
							break;
						case Audited:
							tempTask.setAuditPassNum(myReceiveTask.getAuditNum());
							break;
						case Refuse:
							tempTask.setAuditRefuseNum(myReceiveTask.getAuditNum());
							break;
						default:
							break;
						}
					}
				}
			}
			result.add(tempTask);
		}
		
		return result;
	}

	@Override
	public int getMyReceivedTaskListTotal(TaskReq req) {
		return renRenTaskDao.getMyReceivedTaskListTotal(req);
	}

	@Override
	public List<TaskModel> getSubmittedTaskList(TaskReq req) {
		// 这里判断逻辑，是否可以继续领取任务
		// 1、如果任务结束时间大于当前时间
		// 2、剩余任务量大于0
		// 3、如果当前任务未接单
		return renRenTaskDao.getSubmittedTaskList(req);
	}

	@Override
	public int getSubmittedTaskListTotal(TaskReq req) {
		return renRenTaskDao.getSubmittedTaskListTotal(req);
	}

	/**
	 * 超时取消任务服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	@Override
	public void outTimeCanelTask() {
		renRenTaskDao.outTimeCanelTask();
	}
	/**
	 * 获取任务信息(BYID)
	 * 茹化肖
	 * 2015年11月25日13:06:00
	 * 
	 */
	@Override
	public RenRenTask getTaskInfo(Long taskId) {
		RenRenTask model = renRenTaskDao.selectById(taskId);
		return model;
	}
	/**
	 * 构建任务投放区域列表
	 * 茹化肖
	 * 2015年11月16日16:18:27
	 * @param record
	 * @param regionCodes
	 * @return
	 */
	private List<TaskCityRelation> getCityRelationList(RenRenTask record,
			List<Integer> regionCodes) {
		Map<Integer, String> regionMap = publicProvinceCityService
				.getOpenCityMap();
		List<TaskCityRelation> recordList = new ArrayList<TaskCityRelation>();
		for (Integer regionCode : regionCodes) {
			TaskCityRelation taskCityRelation = new TaskCityRelation();
			taskCityRelation.setTaskId(record.getId());
			taskCityRelation.setCityCode(regionCode);
			taskCityRelation.setCityName("");
			if (regionMap.containsKey(regionCode)) {
				taskCityRelation.setCityName(regionMap.get(regionCode));
			} else if (regionCode == -1) {
				taskCityRelation.setCityName("全部区域");
			}
			recordList.add(taskCityRelation);
		}
		return recordList;
	}
	/***
	 * 修改任务.
	 * V1.0.2需求删除
	 * 备注:茹化肖将修改任务和新建任务放在一起
	 * 
	 */
//	@Override
//	@Transactional(rollbackFor = Exception.class, timeout = 30)
//	public int updateTask(RenRenTask record, List<Integer> regionCodes,
//			List<Attachment> attachments) {
//		RenRenTask oldTaskModel = renRenTaskDao.selectById(record.getId());
//		if (oldTaskModel == null
//				|| (oldTaskModel.getStatus() != TaskStatus.Reject.value() && oldTaskModel
//						.getStatus() != TaskStatus.WaitAudit.value())) {
//			return -1;
//		}
//		BusinessBalance nowBalance = businessBalanceDao
//				.selectByBusinessId(record.getBusinessId());
//		if (nowBalance == null) {
//			throw new TransactionalRuntimeException("没有找到id=" + record.getBusinessId()
//					+ "的商户的余额信息");
//		}
//		Double oldTotalFee = oldTaskModel.getAmount()
//				* oldTaskModel.getTaskTotalCount();
//		Double totalFee = record.getAmount() * record.getTaskTotalCount();
//
//		if (record.getBusinessId().equals(oldTaskModel.getBusinessId())) {
//			Double difFee = totalFee - oldTotalFee;
//			// 如果任务修改后，商户id没有变更,但是任务的费用增加了，则需要判断新增的费用是否小于商家余额
//			if (difFee.compareTo(0d) > 0
//					&& difFee.compareTo(nowBalance.getBalance()) > 0) {
//				return -1;
//			}
//		} else if (totalFee.compareTo(nowBalance.getBalance()) > 0) {
//			// 如果任务修改后，商户id变更了,需要判断变更后的商户的余额是否足够支付任务的费用
//			return -1;
//		}
//		StringBuilder sbRemark = new StringBuilder();
//
//		// 如果任务的合同模板有变更，则重新生成模板的快照
//		Long oldTemplateId = record.getSnapshotTemplateId();
//		String templateremark = updateTemplateSnapshot(record, oldTaskModel);
//		// 没有重新生成模板快照时，当前任务的模板快照id不变
//		if (oldTemplateId.equals(record.getSnapshotTemplateId())) {
//			record.setSnapshotTemplateId(oldTaskModel.getSnapshotTemplateId());
//		}
//		// 如果任务的属性或模板快照有变更，则更新db（在此之前必须先更新快照，否则模板id不对）
//		String taskRemark = getUpdateRemark(record, oldTaskModel);
//		if ((taskRemark != null && !taskRemark.isEmpty())
//				|| !record.getSnapshotTemplateId().equals(
//						oldTaskModel.getSnapshotTemplateId())) {
//			int result = renRenTaskDao.update(record);
//			if (result == 0) {
//				throw new TransactionalRuntimeException("更新任务基础信息时失败");
//			}
//			sbRemark.append(taskRemark);
//		}
//		if (templateremark != null && !templateremark.isEmpty()) {
//			sbRemark.append(templateremark);
//		}
//		// 如果任务的附件有变更，则重新保存附件信息
//		String attachRemark = updateAttachment(record, attachments);
//		if (attachRemark != null && !attachRemark.isEmpty()) {
//			sbRemark.append(attachRemark);
//		}
//		// //如果任务的投放范围有变更，则重新投放范围信息
//		String regionRemark = updateRegion(record, regionCodes);
//		if (regionRemark != null && !regionRemark.isEmpty()) {
//			sbRemark.append(regionRemark);
//		}
//		// 商家id发生了变更，则需要将钱返回给原来的商家
//		if (!record.getBusinessId().equals(oldTaskModel.getBusinessId())) {
//			BusinessBalance oldBalance = businessBalanceDao
//					.selectByBusinessId(oldTaskModel.getBusinessId());
//			if (oldBalance == null) {
//				throw new TransactionalRuntimeException("没有找到id="
//						+ oldTaskModel.getBusinessId() + "的商户的余额信息");
//			}
//			updateBusinessBalance(record.getId(), oldTaskModel.getBusinessId(),
//					oldTotalFee, oldBalance.getBalance(),
//					BBalanceRecordType.CancelTask, record.getModifyName());
//			// 扣除当前商家的余额
//			updateBusinessBalance(record.getId(), record.getBusinessId(), (-1)
//					* totalFee, nowBalance.getBalance(),
//					BBalanceRecordType.ReleaseTask, record.getModifyName());
//		} else if (!oldTotalFee.equals(totalFee)) {
//			// 商家id没变，但是任务的费用发生了变化，则对商户多退少补
//			updateBusinessBalance(record.getId(), record.getBusinessId(),
//					oldTotalFee - totalFee, nowBalance.getBalance(),
//					BBalanceRecordType.UpdateTask, record.getModifyName());
//		}
//		// 记录操作日志
//		if (!sbRemark.toString().isEmpty()) {
//			RenRenTaskLog logRecord = new RenRenTaskLog();
//			logRecord.setRenrenTaskId(record.getId());
//			logRecord.setOptName(record.getModifyName());
//			logRecord.setOptType((short) TaskOpType.Modify.value());
//			logRecord.setRemark(sbRemark.toString());
//			renRenTaskLogDao.insert(logRecord);
//		}
//
//		return 1;
//	}

	private String updateRegion(RenRenTask record, List<Integer> regionCodes) {
		boolean isExist = false;
		StringBuilder sb = new StringBuilder();
		List<TaskCityRelation> oldRelations = taskCityRelationDao
				.selectByTaskId(record.getId());
		List<TaskCityRelation> recordList = getCityRelationList(record,
				regionCodes);

		for (TaskCityRelation relation : recordList) {
			isExist = false;
			for (TaskCityRelation oldrelation : oldRelations) {
				if (relation.getCityCode().equals(oldrelation.getCityCode())) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				sb.append("投放范围新增了" + relation.getCityName() + ",");
			}
		}
		for (TaskCityRelation oldrelation : oldRelations) {
			isExist = false;
			for (TaskCityRelation relation : recordList) {
				if (relation.getCityCode().equals(oldrelation.getCityCode())) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				sb.append("投放范围删除了" + oldrelation.getCityName() + ",");
			}
		}

		if (!sb.toString().isEmpty()) {
			taskCityRelationDao.deleteByTaskId(record.getId());
			taskCityRelationDao.insertList(recordList);
		}
		return sb.toString();
	}
	/**
	 * 更新附件 V1.0.2删除
	 * @param record
	 * @param attachments
	 * @return
	 */
//	private String updateAttachment(RenRenTask record,
//			List<Attachment> attachments) {
//		if (attachments != null && attachments.size() > 0) {
//			for (Attachment attachment : attachments) {
//				attachment.setTaskId(record.getId());
//			}
//		}
//
//		boolean isExist = false;
//		StringBuilder sb = new StringBuilder();
//		List<Attachment> attachList = attachmentDao.selectByTaskId(record
//				.getId());
//
//		for (Attachment attach : attachments) {
//			isExist = false;
//			for (Attachment oldAttach : attachList) {
//				if (attach.getAttachUrl().equals(oldAttach.getAttachUrl())) {
//					isExist = true;
//					break;
//				}
//			}
//			if (!isExist) {
//				sb.append("附件新增了" + attach.getAttachmentName() + ",");
//			}
//		}
//		for (Attachment oldAttach : attachList) {
//			isExist = false;
//			for (Attachment attach : attachments) {
//				if (attach.getAttachUrl().equals(oldAttach.getAttachUrl())) {
//					isExist = true;
//					break;
//				}
//			}
//			if (!isExist) {
//				sb.append("附件删除了" + oldAttach.getAttachmentName() + ",");
//			}
//		}
//
//		if (!sb.toString().isEmpty()) {
//			attachmentDao.deleteByTaskId(record.getId());
//			attachmentDao.insertList(attachments);
//		}
//		return sb.toString();
//	}
	/**
	 * 更新模板快照
	 * V1.0.2需求删除
	 * @param record
	 * @param oldTaskmodel
	 * @return
	 */
//	private String updateTemplateSnapshot(RenRenTask record,
//			RenRenTask oldTaskmodel) {
//		String result = "";
//		boolean needReCreateSnapshot = false;
//		Long newTemplateId = record.getSnapshotTemplateId();
//		TemplateSnapshot oldSnapshotTemplate = templateSnapshotDao
//				.detailById(oldTaskmodel.getSnapshotTemplateId());
//		Template nowTemplate = templateDao.detail(newTemplateId);
//		if (oldSnapshotTemplate != null
//				&& oldSnapshotTemplate.getTemplateId().equals(newTemplateId)) {
//			if (nowTemplate.getLastOptTime().getTime() != oldSnapshotTemplate
//					.getLastOptTime().getTime()) {
//				needReCreateSnapshot = true;
//				result = "更新了合同模板;";
//			}
//		} else {
//			needReCreateSnapshot = true;
//			String oldName = "未知";
//			if (oldSnapshotTemplate != null) {
//				oldName = oldSnapshotTemplate.getTemplateName();
//			}
//			result = "合同模板从:" + oldName + "改为了:"
//					+ nowTemplate.getTemplateName() + ";";
//		}
//		if (needReCreateSnapshot) {
//			TemplateSnapshotReq req = new TemplateSnapshotReq();
//			req.setTemplateId(newTemplateId);
//			int snapshotResult = templateSnapshotDao.copySnapshot(req);
//			if (snapshotResult == 0) {
//				throw new TransactionalRuntimeException("生成任务的模板快照失败");
//			}
//			int detailSnapshotResult = templateDetailSnapshotDao.copySnapshot(
//					req.getTemplateId(), req.getTemplateSnapshotId());
//			if (detailSnapshotResult == 0) {
//				throw new TransactionalRuntimeException("生成任务的模板详情快照失败");
//			}
//			// 重新生成了模板的快照数据之后，才删除原来的模板快照数据
//			templateSnapshotDao
//					.deleteById(oldTaskmodel.getSnapshotTemplateId());
//			templateDetailSnapshotDao.deleteBySnapshotTemplateId(oldTaskmodel
//					.getSnapshotTemplateId());
//			// 将当前任务的模板id设置为模板快照表的id
//			record.setSnapshotTemplateId(req.getTemplateSnapshotId());
//		}
//		return result;
//	}

	private String getUpdateRemark(RenRenTask record, RenRenTask oldModel) {
		if (oldModel == null || record == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		if (!record.getTaskTitle().equals(oldModel.getTaskTitle())) {
			sb.append("任务标题从:" + oldModel.getTaskTitle() + "改为了:"
					+ record.getTaskTitle() + ";");
		}
		if (record.getBeginTime().getTime() != oldModel.getBeginTime()
				.getTime()) {
			sb.append("起止日期的开始日期从:"
					+ ParseHelper.ToDateString(oldModel.getBeginTime(),
							"yyyy-MM-dd")
					+ "改为了:"
					+ ParseHelper.ToDateString(record.getBeginTime(),
							"yyyy-MM-dd") + ";");
		}
		if (record.getEndTime().getTime() != oldModel.getEndTime().getTime()) {
			sb.append("起止日期的结束日期从:"
					+ ParseHelper.ToDateString(oldModel.getEndTime(),
							"yyyy-MM-dd")
					+ "改为了:"
					+ ParseHelper.ToDateString(record.getEndTime(),
							"yyyy-MM-dd") + ";");
		}
		if (!record.getAuditCycle().equals(oldModel.getAuditCycle())) {
			sb.append("审核周期从:" + oldModel.getAuditCycle() + "改为了:"
					+ record.getAuditCycle() + ";");
		}
		if (!record.getTaskTotalCount().equals(oldModel.getTaskTotalCount())) {
			sb.append("任务总数从:" + oldModel.getTaskTotalCount() + "改为了:"
					+ record.getTaskTotalCount() + ";");
		}
		if (!record.getAmount().equals(oldModel.getAmount())) {
			sb.append("单次佣金从:" + oldModel.getAmount() + "改为了:"
					+ record.getAmount() + ";");
		}
		if (!record.getPaymentMethod().equals(oldModel.getPaymentMethod())) {
			sb.append("支付方式从:"
					+ PaymentMethodType.getEnum(oldModel.getPaymentMethod())
							.desc()
					+ "改为了:"
					+ PaymentMethodType.getEnum(record.getPaymentMethod())
							.desc() + ";");
		}
		if (!record.getTaskNotice().equals(oldModel.getTaskNotice())) {
			sb.append("任务公告从:" + oldModel.getTaskNotice() + "改为了:"
					+ record.getTaskNotice() + ";");
		}
		if (!record.getTaskGeneralInfo().equals(oldModel.getTaskGeneralInfo())) {
			sb.append("任务介绍从:" + oldModel.getTaskGeneralInfo() + "改为了:"
					+ record.getTaskGeneralInfo() + ";");
		}
		if (!record.getLink().equals(oldModel.getLink())) {
			sb.append("相关链接从:" + oldModel.getLink() + "改为了:" + record.getLink()
					+ ";");
		}
		if (!record.getTaskNote().equals(oldModel.getTaskNote())) {
			sb.append("注意事项从:" + oldModel.getTaskNote() + "改为了:"
					+ record.getTaskNote() + ";");
		}
		if (!record.getCompanySummary().equals(oldModel.getCompanySummary())) {
			sb.append("公司简介从:" + oldModel.getCompanySummary() + "改为了:"
					+ record.getCompanySummary() + ";");
		}
		if (!record.getTargetPeople().equals(oldModel.getTargetPeople())) {
			sb.append("指派群体从:" + oldModel.getTargetPeople() + "改为了:"
					+ record.getTargetPeople() + ";");
		}
		if (!record.getBusinessId().equals(oldModel.getBusinessId())) {
			Business nowBusiness = businessDao.selectById(record
					.getBusinessId());
			Business oldBusiness = businessDao.selectById(oldModel
					.getBusinessId());
			sb.append("关联商户从:" + oldBusiness.getCompanyName() + "改为了:"
					+ nowBusiness.getCompanyName() + ";");
		}
		return sb.toString();
	}

	@Override
	public List<RenRenTask> getListByTemplateId(Long templateId) {
		return renRenTaskDao.getListByTemplateId(templateId);
	}

	/**
	 * 任务结账服务
	 */
	@Override
	public int settlementTask(Long taskId, String userName) {
		RenRenTask taskModel = renRenTaskDao.selectById(taskId);
		if (taskModel == null
				|| (taskModel.getStatus() != TaskStatus.Expired.value()
						&& taskModel.getStatus() != TaskStatus.Stop.value() || taskModel
						.getStatus().equals(TaskStatus.HasSettlement.value()))) {
			return -1;
		}
		Double realFee = orderDao.getOrderTotalAmount(taskId);
		if (realFee.equals(0d)) {
			return -1;
		}
		BusinessBalance oldBusinessBalance = businessBalanceDao
				.selectByBusinessId(taskModel.getBusinessId());
		if (oldBusinessBalance == null) {
			throw new TransactionalRuntimeException("没有找到id=" + taskModel.getBusinessId()
					+ "的商户的余额信息");
		}
		Double totalFee = taskModel.getAmount() * taskModel.getTaskTotalCount();
		Double difFee = totalFee - realFee;
		if (difFee.compareTo(0d) < 0) {
			throw new TransactionalRuntimeException("id为" + taskId + "的任务的共给地推员的佣金大于了任务总佣金");
		}
		if (difFee.compareTo(0d) > 0) {
			updateBusinessBalance(taskId, taskModel.getBusinessId(), difFee,
					oldBusinessBalance.getBalance(),
					BBalanceRecordType.TaskSettlement, userName);
		}
		UpdateStatusReq statusReq = new UpdateStatusReq();
		statusReq.setOldStatus(taskModel.getStatus());
		statusReq.setReocrdId(taskId);
		statusReq.setStatus(TaskStatus.HasSettlement.value());
		statusReq.setUserName(userName);
		setTaskStatus(statusReq);
		return 1;
	}
	/**
	 * 获取任务的步骤信息
	 * 茹化肖
	 * 2015年11月25日13:23:06
	 */
	@Override
	public ArrayList<TaskSetp> getTaskSetps(Long taskId) {
		ArrayList<TaskSetp> taskSetps=(ArrayList<TaskSetp>)taskSetpDao.getSetpsByTaskId(taskId);
		return taskSetps;
	}
	/**
	 * 获取任务控件信息
	 * 茹化肖
	 * 2015年11月25日13:38:41
	 */
	@Override
	public List<TemplateGroup> getTemplateGroups(Long taskId) {
		List<TemCorModel> corList= templateDetailDao.getTemCorModelsByTaskId(taskId);
		List<TemplateGroup> groups=new ArrayList<TemplateGroup>(); 
		List<Long> groupIdList =corList.stream().map(t->t.getGroupId()).distinct().collect(Collectors.toList());
		for (Long groupid : groupIdList) {
			//构建控件模板
			List<TemplateDetail> childList =corList.stream().filter(t->t.getGroupId().equals(groupid)).map(m->{
				
				TemplateDetail ab=	new TemplateDetail();
				ab.setTitle(m.getControlTitle());
				ab.setName(m.getName());
				ab.setOrderNum(m.getOrderNum());
				ab.setDefaultValue(m.getDefaultValue());
				ab.setControlData(m.getControlData());
				ab.setControlType(m.getControlType());
				return ab;
				
			}).collect(Collectors.toList());
			//构建模板组
			TemCorModel temp=corList.stream().filter(t->t.getGroupId().equals(groupid)).findFirst().get();
			TemplateGroup groupaGroup=new TemplateGroup();
			groupaGroup.setGroupType(temp.getGroupType());
			groupaGroup.setId(groupid);
			groupaGroup.setTaskId(taskId);
			groupaGroup.setTitle(temp.getGroupTitle());
			groupaGroup.setTemplateList(childList);
			groups.add(groupaGroup);		
			}
		return groups;
	}
}
