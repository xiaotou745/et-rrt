package com.renrentui.renrenadmin.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.dao.inter.IStrategyDao;
import com.renrentui.renrenapi.service.inter.IBusinessBalanceService;
import com.renrentui.renrenapi.service.inter.IBusinessService;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrenapi.service.inter.ISubCommissionService;
import com.renrentui.renrenapi.service.inter.ITemplateService;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.enums.TemplateStatus;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.RenRenTaskDetail;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TaskSetp;
import com.renrentui.renrenentity.domain.TemplateGroup;
import com.renrentui.renrenentity.req.AesParameterReq;
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
			
		}
		List<Business> datalist=businessService.getAllList();
		model.addObject("businessData", datalist);
		model.addObject("provincelist", publicProvinceCityService.getOpenCityByJiBie(2));//省份
		List<PublicProvinceCity> citylistlist =publicProvinceCityService.getOpenCityByJiBie(3);//城市
		model.addObject("pro_city", getCityStr(citylistlist));//构建城市字符串
		model.addObject("childs",subCommissionService.getCruuentStrategyChild());
		return model;
	}

	private String getTemplateList() {
		PagedTemplateReq req=new PagedTemplateReq();
		List<Template> templateList= templateService.getAllList(req);
		Map<Long, StringBuilder> resulMap=new HashMap<Long, StringBuilder>();
		for (Template item : templateList) {
			if (resulMap.containsKey(item.getBusinessId())) {
				resulMap.get(item.getBusinessId()).append(";"+item.getId()+"|"+item.getTemplateName()+"|"+item.getStatus());
			}else {
				StringBuilder builder=new StringBuilder();
				builder.append(item.getId()+"|"+item.getTemplateName()+"|"+item.getStatus());
				resulMap.put(item.getBusinessId(), builder);
			}
		}
		StringBuilder resultBuilder=new StringBuilder();
		for (Map.Entry<Long, StringBuilder> entry : resulMap.entrySet()) {  
			if (resultBuilder.toString().isEmpty()) {
				resultBuilder.append(entry.getKey()+"="+entry.getValue().toString());
			}else {
				resultBuilder.append("#"+entry.getKey()+"="+entry.getValue().toString());
			}
			
		}  

		return resultBuilder.toString();
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
//	private List<PublicProvinceCity> getOpenCityByJiBie(List<PublicProvinceCity> list,int jiBie)
//	{
//		List<PublicProvinceCity> listnew = new ArrayList<PublicProvinceCity>();
//		for (PublicProvinceCity item : list) {
//			if (item.getJiBie() == jiBie) {
//				listnew.add(item);
//			}
//		}
//		return listnew;
//	}
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
		taskItem.setStatus(TaskStatus.WaitAudit.value());
		UserContext context=UserContext.getCurrentContext(request);
		taskItem.setCreateName(context.getUserName());
		taskItem.setModifyName(context.getUserName());
		List<Integer> regionCodes=getRegionCodeList(request,req);//获取城市信息
		List<Attachment> attachments=null;//TODO 暂时将附件去掉
		return renRenTaskService.insert(req, regionCodes,attachments);
	}
	/**
	 * 组建任务区域
	 * @param request
	 * @return
	 */
	private List<Integer> getRegionCodeList(HttpServletRequest req,SaveTaskReq request){
		List<Integer> regionCodes=new ArrayList<>();
		Integer provinceCode=ParseHelper.ToInt(request.getProvinceCode(),0);
		if (provinceCode>-1) {
			Integer cityCode=ParseHelper.ToInt(request.getCityCode(),0);
			if (cityCode>-1) {
				regionCodes.add(cityCode);
			}else {
				regionCodes.add(provinceCode);
			}
		}else {
			regionCodes.add(-1);
		}
		return regionCodes;
	}
	private List<Attachment> getAttachList(HttpServletRequest request){
		List<Attachment> attachments=new ArrayList<>();
		String attachs=request.getParameter("attachmentfiles");
		if (attachs!=null&&!attachs.isEmpty()) {
			String [] attachList=attachs.split(";");
			for (String fileinfo : attachList) {
				String [] fileNames=fileinfo.split("#");
				Attachment attach=new Attachment();
				attach.setAttachmentName(fileNames[0]);
				attach.setAttachUrl(fileNames[1]);
				attachments.add(attach);
			}
		}
		return attachments;
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
		return model;
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
//		//4 获取投放放范围
//		model.addObject("provincelist", publicProvinceCityService.getOpenCityByJiBie(2));//省份
//		List<PublicProvinceCity> citylistlist =publicProvinceCityService.getOpenCityByJiBie(3);//城市
		model.addObject("pro_city", publicProvinceCityService.getTaskCity(taskId));
		return model;
	}
	
	/**
	 * 更新任务 .V1.0.2删除 茹化肖
	 * 备注.任务修改和新建任务放在同一接口
	 * @param request
	 * @param taskItem
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
//	@RequestMapping("updatetask")
//	@ResponseBody
//	public int updateTask(HttpServletRequest request,RenRenTask taskItem,String beginDate,String endDate) {
//		taskItem.setPusher("");
//		taskItem.setStatus(TaskStatus.WaitAudit.value());
//		taskItem.setBeginTime(ParseHelper.ToDate(beginDate));
//		taskItem.setEndTime(ParseHelper.ToDate(endDate));
//		taskItem.setAvailableCount(taskItem.getTaskTotalCount());
//		UserContext context=UserContext.getCurrentContext(request);
//		taskItem.setModifyName(context.getUserName());
//		List<Integer> regionCodes=getRegionCodeList(request,null);
//		List<Attachment> attachments=getAttachList(request);
//		return renRenTaskService.updateTask(taskItem, regionCodes,attachments);
//	}
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
	 * 任务结账
	 * @param request
	 * @param taskId
	 * @author hailongzhao
	 * @date 20151014
	 * @return
	 */
	@RequestMapping("settlementtask")
	@ResponseBody
	public int settlementTask(HttpServletRequest request,Long taskId){
		UserContext context=UserContext.getCurrentContext(request);
		return renRenTaskService.settlementTask(taskId, context.getUserName());
	}
}
