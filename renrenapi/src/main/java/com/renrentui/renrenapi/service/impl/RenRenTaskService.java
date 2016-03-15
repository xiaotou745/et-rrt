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
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IClienterRelationDao;
import com.renrentui.renrenapi.dao.inter.IOrderChildDao;
import com.renrentui.renrenapi.dao.inter.IOrderLogDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskLogDao;
import com.renrentui.renrenapi.dao.inter.IStrategyDao;
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
import com.renrentui.renrencore.enums.AreaLevel;
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
import com.renrentui.renrenentity.ClienterRelation;
import com.renrentui.renrenentity.Order;
import com.renrentui.renrenentity.OrderChild;
import com.renrentui.renrenentity.OrderLog;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.RenRenTaskLog;
import com.renrentui.renrenentity.Strategy;
import com.renrentui.renrenentity.StrategyChild;
import com.renrentui.renrenentity.TaskCityRelation;
import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.TemplateDetail;
import com.renrentui.renrenentity.TemplateSnapshot;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.domain.CheckSubmitTask;
import com.renrentui.renrenentity.domain.ClienterTask;
import com.renrentui.renrenentity.domain.MyReceiveTask;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.domain.OrderRetrunModel;
import com.renrentui.renrenentity.domain.PartnerDetail;
import com.renrentui.renrenentity.domain.ReceiveNum;
import com.renrentui.renrenentity.domain.RenRenTaskDetail;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TabModel;
import com.renrentui.renrenentity.domain.TaskDatum;
import com.renrentui.renrenentity.domain.TaskDatumChild;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.domain.TaskPartnerItem;
import com.renrentui.renrenentity.domain.TaskRegion;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemCorModel;
import com.renrentui.renrenentity.domain.TemplateGroup;
import com.renrentui.renrenentity.req.BusinessBalanceReq;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.PagedPartnerReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.PartnerListReq;
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
	@Autowired
	private IStrategyDao strategyDao;
	@Autowired
	private IClienterRelationDao clienterRelationDao;
	@Autowired
	private IClienterDao clienterDao;
	/**
	 * 获取任务详情 茹化肖 2015年9月29日13:00:35
	 * 修改时间 2015年11月19日11:20:38
	 * 修改人  
	 */
	@Override
	public TaskDetail getTaskDetail(TaskDatumDetailReq req) {

		TaskDetail detail = new TaskDetail();
	 	RenRenTask task= renRenTaskDao.getTaskDetail(req);//获取任务 任务信息
	 	if (task==null) {
			return null;
		}
	 	if (req.getUserId()>0&&
	 		task.getTaskType().intValue()!=TaskType.ContractTask.value()&&
	 		task.getDownUrl()!=null&&
	 		!task.getDownUrl().isEmpty()) {
	 		String downUrl="%s/clienter/sharetask?taskId=%s&clienterId=%s&downUrl=%s";
	 		downUrl=String.format(downUrl,PropertyUtils.getProperty("java.renrenadmin.url"),req.getTaskId(),req.getUserId(),task.getDownUrl());
	 		task.setDownUrl(downUrl);
		}
	 	//获取任务步骤 等信息
	 	List<TaskSetp> taskSetps=taskSetpDao.getSetpsByTaskId(req.getTaskId());
	 	PartnerListReq partnerReq=new PartnerListReq();
	 	partnerReq.setItemsCount(5);
	 	partnerReq.setNextId(0);
	 	partnerReq.setTaskId(req.getTaskId());
		List<PartnerDetail> partnerList= clienterDao.getClienterListByTaskId(partnerReq);//获取任务参与人
		long partnerTotal= clienterDao.getClienterListByTaskIdTotal(req.getTaskId());//获取任务参与人总数
		detail.setPartnerTotal(partnerTotal);
	 	detail.setTask(task);
	 	detail.setTaskSetps(taskSetps);
	 	detail.setPartnerList(partnerList);
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
		cTask.setCityCode(req.getCityCode());
		Long res =renRenTaskDao.insertClienterTask(cTask);
		if(res>-1)//成功插入
		{
			renRenTaskDao.updatePartnerNum(req.getTaskId());
			model.setOrderId(res);
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

//	/**
//	 * 取消任务 茹化肖 2015年9月30日13:24:27
//	 */
//	@Override
//	@Transactional(rollbackFor = Exception.class, timeout = 30)
//	public CancelTaskCode cancelTask(CancelTaskReq req) {
//		// 验证订单相关信息
//		CheckCancelOrder check = orderDao.checkCancelOrder(req);
//		if (check == null)// 订单不存在
//			return CancelTaskCode.OrderNull;
//		// if (check.getCancelCan() == 0)// 订单不能取消 //窦海超 去掉了判断订单进行中-未完成不能取消状态
//		// {
//		if (check.getIsCancle() == 1)// 订单已经取消
//			return CancelTaskCode.TaskIsCancel;
//		// if (check.getIsComplete() == 1)// 订单已经完成不能取消
//		// return CancelTaskCode.TaskComplete;
//		// return CancelTaskCode.CantCancel;// 订单不可取消
//		// }
//
//		int res = orderDao.cancelOrder(req);// 取消订单
//
//		OrderLog orderLog = new OrderLog();
//		orderLog.setOrderNo(check.getOrderNo());
//		orderLog.setOrderId(req.getOrderId());
//		orderLog.setOptType(Short.valueOf("1"));
//		orderLog.setOptName("地推员:" + req.getUserId());
//		orderLog.setRemark("地推员:" + req.getUserId() + "取消订单:"
//				+ check.getOrderNo());
//		int orderlogres = orderLogDao.addOrderLog(orderLog);// 记录订单操作日志
//
//		int addres = renRenTaskDao.addTaskAvailableCount(check.getTaskId());// 增加任务的剩余量
//		if (res > 0 && orderlogres > 0 && addres > 0) {
//			return CancelTaskCode.Success;
//		} else {
//			Error error = new Error("取消任务错误");
//			throw new RuntimeErrorException(error);
//		}
//	}
//
	/**
	 * 提交任务 茹化肖 2015年10月8日13:37:08
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public SubmitTaskCode submitTask(SubmitTaskReq req) {
		//验证订单是否可以提交
		CheckSubmitTask check = renRenTaskDao.checkOrderSubmit(req);
		if (check == null)
			return SubmitTaskCode.CantSubmit;
		if (check.getTaskClosed() == 1)// 任务已关闭 不可提交
			return SubmitTaskCode.TaskClosed;
		//1.插入TaskDatum 任务资料表
		TaskDatum taskDatum=new TaskDatum();
		taskDatum.setCtId(req.getCtId());
		taskDatum.setClienterId(req.getUserId());
		taskDatum.setTaskId(req.getTaskId());
		//查询当前分佣策略
		Strategy stra=strategyDao.getCruuentStrategy();
		long strategyId=0l;
		Double subCommisson=0.00;//累计分佣金额
		if(stra!=null){
			strategyId=stra.getId();
			//计算上级累计分佣
			//1.查找我的层级
			int  clienterLevel=clienterRelationDao.getLevelByClienterId(req.getUserId());
			//查找我绑定的分佣关系
			List<StrategyChild> strChilds=strategyDao.getStrategyChildById(strategyId);
			if(clienterLevel>1&&strChilds!=null&&strChilds.size()>0)
			{
				//取等级 或者 层级 最小的进行循环 上级分佣人次数应该是 我的层级-1 或者分佣等级(取两个中的最小)
				int size=clienterLevel-1>strChilds.size()?strChilds.size():clienterLevel-1;
				Double singleMoney=0.00;
				for (int i = 0; i < size; i++) {
					//任务累计分佣+=等级比例*任务金额*0.01 因为比例存的是0-99.99 Doule
					//0.019需要舍弃为0.01,需要先舍后加，否则累计分佣和给每个上级的分佣之和对不上
					singleMoney=strChilds.get(i).getPercentage()*check.getTaskAmount()*0.01;
					subCommisson+=ParseHelper.subNum(singleMoney, 2);
				}
			}
		}

		taskDatum.setStrategyId(strategyId);
		taskDatum.setSubCommisson(subCommisson);
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
		
		

	}
	/**
	 * 保存任务
	 * 茹化肖
	 * 2015年11月16日11:36:42
	 * remark:新业务需求修改
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int insert(SaveTaskReq taskreq) {
	    RenRenTask	task=taskreq.getRenRenTask();
	    //1.插入任务信息
	    if(task.getId()>0)
	    {
	    	//清除任务下的步骤,控件,控件组,投放城市
	    	renRenTaskDao.clearTaskInfo(task.getId());
	    	//更新任务信息
	    	renRenTaskDao.updateTaskInfo(task);
	    	//更新日志
	    	RenRenTaskLog logRecord = new RenRenTaskLog();
			logRecord.setRenrenTaskId(task.getId());
			logRecord.setOptName(task.getCreateName());
			logRecord.setOptType((short) TaskOpType.Modify.value());
			logRecord.setRemark(TaskOpType.Modify.desc());
			renRenTaskLogDao.insert(logRecord);
	    }
	    else {
	    	 //插入任务信息
	    	 int result = renRenTaskDao.insert(task);
	    	//插入操作日志
	    	RenRenTaskLog logRecord = new RenRenTaskLog();
	 		logRecord.setRenrenTaskId(task.getId());
	 		logRecord.setOptName(task.getCreateName());
	 		logRecord.setOptType((short) TaskOpType.NewTask.value());
	 		logRecord.setRemark(TaskOpType.NewTask.desc());
	 		renRenTaskLogDao.insert(logRecord);
		    for (TaskCityRelation taskRegion : taskreq.getTaskRegions()) {
		    	taskRegion.setTaskId(task.getId());
			}
		}
	   
	    //2. 插入步骤信息
	    List<TaskSetp> setplist=taskreq.getTaskSetps();
	    for (int i = 0; i < setplist.size(); i++) {
	    	TaskSetp setp=setplist.get(i);
	    	setp.setTaskId(task.getId());//设置任务ID
	    	if(setp.getSetpType()==3)//细则
	    	{
	    		setp.setContent(setp.getContent().trim());
	    	}
	    	renRenTaskDao.insertTaskSetp(setp);
		}
	    //3.插入模板组信息
	    List<TemplateGroup> groups=taskreq.getTemplateGroup();
	    if(task.getTaskType()!=TaskType.ContractTask.value())//非签约任务 没有模板信息
	    {
	    	groups=new ArrayList<TemplateGroup>();
	    }
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
		taskCityRelationDao.insertList(taskreq.getTaskRegions());
	    
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
		if (req.getCityName()!=null&&!req.getCityName().isEmpty()) {
			List<PublicProvinceCity> cityLists=publicProvinceCityService.getOpenCityByJiBie(AreaLevel.City);
			List<PublicProvinceCity> citys=cityLists.stream().filter(k->k.getName().contains(req.getCityName())).collect(Collectors.toList());
			List<Integer> cityCodes=citys.stream().map(k->k.getCode()).collect(Collectors.toList());
			List<Integer> proCodes=citys.stream().map(k->k.getParentCode()).collect(Collectors.toList());
			req.setAreaCodeList(new ArrayList<Integer>());
			req.getAreaCodeList().add(-1);
			if (cityCodes.size()>0||proCodes.size()>0) {
				req.getAreaCodeList().addAll(proCodes);
				req.getAreaCodeList().addAll(cityCodes);
			}
		}
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
			itemMsg.setTitle("任务关闭通知");
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
		if (req.getCurrentPage()<=0) {
			req.setCurrentPage(1);  //默认第一页
		}
		if(req.getPageSize()<=0){
			req.setPageSize(15);  //默认页容量
		}
		
		if(req.getPlatform().toLowerCase().equals("ios") && PropertyUtils.getProperty("IsHideDownLoadTask").equals("1"))
		{
			req.setIsHideDown(1);
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
		List<MyReceiveTask> resultList= renRenTaskDao.getMyReceivedTaskList(req);
		String downUrl="%s/clienter/sharetask?taskId=%s&clienterId=%s&downUrl=%s";
		String adminHost=PropertyUtils.getProperty("java.renrenadmin.url");
		String finalDownUrl="";
		for (MyReceiveTask task : resultList) {
			if (task.getTaskType()!=TaskType.ContractTask.value()&&
		 		task.getDownUrl()!=null&&
		 		!task.getDownUrl().isEmpty()){
				finalDownUrl=String.format(downUrl,adminHost,task.getTaskId(),req.getUserId(),task.getDownUrl());
		 		task.setDownUrl(finalDownUrl);	
			}
		}
		return resultList;
	}

	@Override
	public ReceiveNum  getMyReceivedTaskListTotal(TaskReq req) {
		return renRenTaskDao.getMyReceivedTaskListTotal(req);
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

//	/**
//	 * 任务结账服务
//	 */
//	@Override
//	public int settlementTask(Long taskId, String userName) {
//		RenRenTask taskModel = renRenTaskDao.selectById(taskId);
//		if (taskModel == null
//				|| (taskModel.getStatus() != TaskStatus.Expired.value()
//						&& taskModel.getStatus() != TaskStatus.Stop.value() || taskModel
//						.getStatus().equals(TaskStatus.HasSettlement.value()))) {
//			return -1;
//		}
//		Double realFee = orderDao.getOrderTotalAmount(taskId);
//		if (realFee.equals(0d)) {
//			return -1;
//		}
//		BusinessBalance oldBusinessBalance = businessBalanceDao
//				.selectByBusinessId(taskModel.getBusinessId());
//		if (oldBusinessBalance == null) {
//			throw new TransactionalRuntimeException("没有找到id=" + taskModel.getBusinessId()
//					+ "的商户的余额信息");
//		}
//		Double totalFee = taskModel.getAmount() * taskModel.getTaskTotalCount();
//		Double difFee = totalFee - realFee;
//		if (difFee.compareTo(0d) < 0) {
//			throw new TransactionalRuntimeException("id为" + taskId + "的任务的共给地推员的佣金大于了任务总佣金");
//		}
//		if (difFee.compareTo(0d) > 0) {
//			updateBusinessBalance(taskId, taskModel.getBusinessId(), difFee,
//					oldBusinessBalance.getBalance(),
//					BBalanceRecordType.TaskSettlement, userName);
//		}
//		UpdateStatusReq statusReq = new UpdateStatusReq();
//		statusReq.setOldStatus(taskModel.getStatus());
//		statusReq.setReocrdId(taskId);
//		statusReq.setStatus(TaskStatus.HasSettlement.value());
//		statusReq.setUserName(userName);
//		setTaskStatus(statusReq);
//		return 1;
//	}
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
	
	/**
	 * 资料数据导出
	 * 茹化肖
	 */
	@Override
	public List<OrderAudit> taskDaumExport(Long taskId) {
		return renRenTaskDao.taskDaumExport(taskId );
	}

	@Override
	public PagedResponse<TaskPartnerItem> getPagedTaskPartnerList(
			PagedPartnerReq req) {
return renRenTaskDao.getPagedTaskPartnerList(req);
	}
}
