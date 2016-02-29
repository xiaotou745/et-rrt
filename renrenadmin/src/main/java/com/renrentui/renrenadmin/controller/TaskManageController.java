package com.renrentui.renrenadmin.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IBusinessBalanceService;
import com.renrentui.renrenapi.service.inter.IBusinessService;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrenapi.service.inter.ISubCommissionService;
import com.renrentui.renrenapi.service.inter.ITaskCityRelationService;
import com.renrentui.renrenapi.service.inter.ITaskTagService;
import com.renrentui.renrenapi.service.inter.ITemplateService;
import com.renrentui.renrencore.enums.AreaLevel;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.enums.TaskType;
import com.renrentui.renrencore.enums.TemplateStatus;
import com.renrentui.renrencore.util.ExcelUtils;
import com.renrentui.renrencore.util.HttpUtil;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.TaskTag;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskPartnerItem;
import com.renrentui.renrenentity.domain.TaskRegion;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemplateGroup;
import com.renrentui.renrenentity.req.PagedPartnerReq;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.PagedTemplateReq;
import com.renrentui.renrenentity.req.SaveTaskReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;


@Controller
@RequestMapping("taskmanage")
public class TaskManageController {
	@Autowired
	private ITemplateService templateService;
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IPublicProvinceCityService publicProvinceCityService;
	@Autowired
	private IRenRenTaskService renRenTaskService;
	@Autowired
	private IBusinessBalanceService businessBalanceService;
	@Autowired
	private ISubCommissionService  subCommissionService;
	@Autowired
	private ITaskCityRelationService taskCityRelationService;
	@Autowired
	private ITaskTagService taskTagService;
	
	/**
	 * 新建任务页面
	 * 茹化肖
	 * 2015年11月16日16:26:53
	 * 
	 * @return
	 */
	@RequestMapping("newtask")
	public ModelAndView newTask(Long taskId) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "任务管理");
		model.addObject("currenttitle", "新建任务");
		model.addObject("viewPath", "taskmanage/newtask");
		if(taskId!=null&&taskId>0)
		{//修改任务
			//1.查询任务信息
			//1获取任务信息
			RenRenTask taskInfo=renRenTaskService.getTaskInfo(taskId);
			if (taskInfo==null) {
				throw new RuntimeException("id="+taskId+"的任务不存在");
			}
			//2.获取步骤信息
			ArrayList<TaskSetp> taskSetps =renRenTaskService.getTaskSetps(taskId);
			//3 获取控件信息
			if(taskInfo.getTaskType()==1)
			{
				List<TemplateGroup> groups=renRenTaskService.getTemplateGroups(taskId);
				model.addObject("groups", groups);
			}
			model.addObject("taskSetps", taskSetps);
			model.addObject("taskInfo", taskInfo);
			model.addObject("taskID", taskId);
			model.addObject("taskCityInfo", getTaskRegions(taskId));
			
		}
		List<Business> datalist=businessService.getAllList();
		model.addObject("businessData", datalist);
	
		model.addObject("childs",subCommissionService.getCruuentStrategyChild());
		List<TaskTag> tagList=taskTagService.getAll();
		model.addObject("tagList", tagList);
		model.addObject("provincelist", publicProvinceCityService.getOpenCityByJiBie(AreaLevel.Province));//省份
		List<PublicProvinceCity> citylistlist =publicProvinceCityService.getOpenCityByJiBie(AreaLevel.City);//城市
		Map<Integer, List<PublicProvinceCity>> provinceCityMap =citylistlist.stream().collect(Collectors.groupingBy(PublicProvinceCity::getParentCode));
		model.addObject("provinceCityMap", provinceCityMap);
		return model;
	}

	private String getCityStr(List<PublicProvinceCity> list){
		Map<Integer, StringBuilder> resulMap=new HashMap<Integer, StringBuilder>();
		for (PublicProvinceCity item : list) {
			if (resulMap.containsKey(item.getParentCode())) {
				resulMap.get(item.getParentCode()).append(";"+item.getCode()+"|"+item.getName());
			}else {
				StringBuilder builder=new StringBuilder();
				builder.append(item.getCode()+"|"+item.getName());
				resulMap.put(item.getParentCode(), builder);
			}
		}
		StringBuilder resultBuilder=new StringBuilder();
		for (Map.Entry<Integer, StringBuilder> entry : resulMap.entrySet()) {  
			if (resultBuilder.toString().isEmpty()) {
				resultBuilder.append(entry.getKey()+"="+entry.getValue().toString());
			}else {
				resultBuilder.append("#"+entry.getKey()+"="+entry.getValue().toString());
			}
			
		}  

		return resultBuilder.toString();
	}

	/**
	 * 保存.修改
	 * 茹化肖
	 * 2015年11月16日11:13:58
	 * @param request
	 * @param req
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping("savetask")
	@ResponseBody
	public int saveTask(HttpServletRequest request,String data) {
		
		SaveTaskReq req=JsonUtil.str2obj(data,SaveTaskReq.class);
		RenRenTask taskItem=req.getRenRenTask();
		//如果是下载类或分享类任务，需要校验地址是否可以打开
		if (taskItem.getTaskType().intValue()!=TaskType.ContractTask.value()&&
			taskItem.getDownUrl()!=null&&
			!taskItem.getDownUrl().isEmpty()) {
			try {
				HttpUtil.sendGet(taskItem.getDownUrl(), "");
			} catch (Exception e) {
				return -1;
			}
		}
		
		taskItem.setStatus(TaskStatus.WaitAudit.value());
		UserContext context=UserContext.getCurrentContext(request);
		taskItem.setCreateName(context.getUserName());
		taskItem.setModifyName(context.getUserName());
		return renRenTaskService.insert(req);
	}

	/**
	 * 审核任务 列表
	 * @return
	 */
	@RequestMapping("audittask")
	public ModelAndView audiTask() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "任务管理");
		model.addObject("currenttitle", "审核任务");
		model.addObject("viewPath", "taskmanage/audittask");
		List<Business> datalist=businessService.getAllList();
		model.addObject("businessData", datalist);
		PagedTemplateReq req=new PagedTemplateReq();
		req.setStatus(TemplateStatus.Valid.value());
		List<Template> templatelist=templateService.getAllList(req);
		model.addObject("templatelist", templatelist);
		return model;
	}
	/**
	 * 审核任务 列表  数据
	 * @param req
	 * @return
	 */
	@RequestMapping("audittaskdo")
	public ModelAndView audiTaskDo(PagedRenRenTaskReq req) {
		ModelAndView model = new ModelAndView("taskmanage/audittaskdo");
		PagedResponse<RenRenTaskModel> resp=renRenTaskService.getPagedRenRenTaskList(req);
		model.addObject("listData", resp);
		List<Long> taskIds=resp.getResultList().stream().map(k->k.getId()).collect(Collectors.toList());
		model.addObject("taskRegionMap", getTaskRegionMap(taskIds));
		return model;
	}
	private Map<Long,String> getTaskRegionMap(List<Long> taskIds){
		Map<Long, List<TaskRegion>> taskRegionMap = taskCityRelationService.getTaskCityRelationDetailList(taskIds);
		Map<Long, String> regionMap = new HashMap<>();
		for (Long taskId : taskRegionMap.keySet()) {
			List<String> cityStrList = new ArrayList<>();
			if (taskRegionMap.get(taskId).size() == 1&& taskRegionMap.get(taskId).get(0).getCityCode() == -1) {
				cityStrList.add(taskRegionMap.get(taskId).get(0).getCityName());
			} else {
				Map<Integer, List<TaskRegion>> group = taskRegionMap.get(taskId)
						.stream().collect(Collectors.groupingBy(TaskRegion::getParentCode));
				for (Integer parentCode : group.keySet()) {
					if (parentCode.intValue() > 0) {
						String proName = group.get(parentCode).get(0).getParentName();
						List<String> cityNames = group.get(parentCode).stream().map(m -> m.getCityName()).collect(Collectors.toList());
						cityStrList.add(proName + ":"+ String.join(",", cityNames));
					} else {
						List<String> cityNames = group.get(parentCode).stream().map(m -> m.getCityName() + ":全省").collect(Collectors.toList());
						cityStrList.addAll(cityNames);
					}
				}
				Collections.sort(cityStrList, new Comparator<String>() {
					public int compare(String arg0, String arg1) {
						return new Integer(arg0.length()).compareTo(new Integer(arg1.length()));
					}
				});
			}
			regionMap.put(taskId, String.join("<br/>", cityStrList));
		}
		return regionMap;
	}
	/**
	 * 修改任务状态 (审核 取消.驳回,终止)
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping("settaskstatus")
	@ResponseBody
	public int setTaskStatus(HttpServletRequest request,UpdateStatusReq req) {
		UserContext context=UserContext.getCurrentContext(request);
		req.setUserName(context.getUserName());
		return renRenTaskService.setTaskStatus(req);
	}
	/**
	 * 任务详情页面
	 * 茹化肖
	 * 2015年11月25日11:41:50
	 * @param taskId
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView taskDetail(Long taskId) {
		if (taskId==null||taskId<0) {
			throw new RuntimeException("taskId不能为空");
		}
		ModelAndView model = new ModelAndView("adminView");
		//1获取任务信息
		RenRenTask taskInfo=renRenTaskService.getTaskInfo(taskId);
		if (taskInfo==null) {
			throw new RuntimeException("id="+taskId+"的任务不存在");
		}
		//2.获取步骤信息
		ArrayList<TaskSetp> taskSetps =renRenTaskService.getTaskSetps(taskId);
		//3 获取控件信息
		List<TemplateGroup> groups=null;
		if(taskInfo.getTaskType()==1)
		{
			 groups=renRenTaskService.getTemplateGroups(taskId);
			
		}
		else {
			groups=new ArrayList<TemplateGroup>();
		}
		model.addObject("groups", groups);
		model.addObject("subtitle", "任务管理");
		model.addObject("currenttitle", "任务详情");
		model.addObject("viewPath", "taskmanage/detail");
		model.addObject("taskSetps", taskSetps);
		
		model.addObject("taskInfo", taskInfo);

		List<TaskTag> tagList=taskTagService.getAll();
		model.addObject("tagList", tagList);
		
		model.addObject("provincelist", publicProvinceCityService.getOpenCityByJiBie(AreaLevel.Province));//省份
		List<PublicProvinceCity> citylistlist =publicProvinceCityService.getOpenCityByJiBie(AreaLevel.City);//城市
		Map<Integer, List<PublicProvinceCity>> provinceCityMap =citylistlist.stream().collect(Collectors.groupingBy(PublicProvinceCity::getParentCode));
		model.addObject("provinceCityMap", provinceCityMap);
//		//4 获取投放放范围
		model.addObject("taskCityInfo", getTaskRegions(taskId));
		return model;
	}
	private String getTaskRegions(long taskId){
		List<Long> taskIds=new ArrayList<>();
		taskIds.add(taskId);
		Map<Long,List<TaskRegion>> resultMap=taskCityRelationService.getTaskCityRelationDetailList(taskIds);
		List<TaskRegion> reslut= resultMap.get(taskId);
		List<String> proCodes=reslut.stream().filter(m->m.getParentCode()==0).map(k->k.getCityCode()+"").collect(Collectors.toList());
		List<String> cityCodes=reslut.stream().filter(m->m.getParentCode()>0).map(k->k.getCityCode()+"").collect(Collectors.toList());
		return String.join(",", proCodes)+"#"+String.join(",", cityCodes);
	}
	@RequestMapping("getbusinessbanlance")
	@ResponseBody
	public String getBusinessBanlance(Long businessId){
		BusinessBalance balance= businessBalanceService.selectByBusinessId(businessId);
		if (balance!=null) {
			return ParseHelper.digitsNum(balance.getBalance(),2);
		}
		return "0.00";
	}
	/**
	 * 任务数据导出
	 * @param taskId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("taskexport")
	@ResponseBody
	public void taskExport(Long taskId,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		List<OrderAudit> records=renRenTaskService.taskDaumExport(taskId);
		String fileName = "人人地推-资料数据数据"+taskId;
		//fileName = String.format(fileName, "到" + ParseHelper.ToDateString(req.getEndDate(),"yyyy-MM-dd"));
		LinkedHashMap<String, String> columnTitiles = new LinkedHashMap<String, String>();
		columnTitiles.put("资料编号", "id");
		columnTitiles.put("地推员", "clienterInfo");
		columnTitiles.put("公司名称", "pusher");
		columnTitiles.put("任务名称", "taskTitle");
		columnTitiles.put("该地推员完成次数", "completeNum");
		columnTitiles.put("总佣金/次", "totalAmount");
		columnTitiles.put("地推员佣金/次", "amountStr");
		columnTitiles.put("上级累计分佣", "subCommisson");
		columnTitiles.put("盈亏", "profitAmount");
		columnTitiles.put("提交时间", "finishTime");
		columnTitiles.put("终审时间", "auditTime");
		columnTitiles.put("审核状态", "auditStatus");
		columnTitiles.put("资料数据", "dataValue");
		ExcelUtils.export2Excel(fileName, "资料审核列表", columnTitiles,records, request, response);
		return;
	}
	@RequestMapping("partnerlist")
	public ModelAndView partnerList(Long taskId,String taskTitle,String taskType,String taskStatus){
		if (taskId==null||taskId.longValue()<=0) {
			return null;
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "任务管理");
		model.addObject("currenttitle", "任务参与人列表");
		model.addObject("viewPath", "taskmanage/partnerlist");
		model.addObject("taskId", taskId);
		model.addObject("taskTitle", taskTitle==null?"":taskTitle);
		model.addObject("taskType", taskType==null?"":taskType);
		model.addObject("taskStatus", taskStatus==null?"":taskStatus);
		return model;
	}
	@RequestMapping("partnerlistdo")
	public ModelAndView partnerListdo(PagedPartnerReq req){
		if (req==null||req.getTaskId()<0) {
			return null;
		}
		if (req.getTaskTitle()!=null) {
			req.setTaskTitle(req.getTaskTitle().trim());
		}
		if (req.getCityName()!=null) {
			req.setCityName(req.getCityName().trim());
		}
		if (req.getClienterName()!=null) {
			req.setClienterName(req.getClienterName().trim());
		}
		if (req.getClienterPhoneNo()!=null) {
			req.setClienterPhoneNo(req.getClienterPhoneNo().trim());
		}
		ModelAndView model = new ModelAndView("taskmanage/partnerlistdo");
		PagedResponse<TaskPartnerItem> list=renRenTaskService.getPagedTaskPartnerList(req);
		model.addObject("listData", list);
		return model;
	}
}
