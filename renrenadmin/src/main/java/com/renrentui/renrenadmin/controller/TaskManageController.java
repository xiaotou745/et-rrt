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
import com.renrentui.renrenapi.service.inter.IBusinessService;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrenapi.service.inter.ITemplateService;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;


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
	@RequestMapping("newtask")
	public ModelAndView newTask() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "任务管理");
		model.addObject("currenttitle", "新建任务");
		model.addObject("viewPath", "taskmanage/newtask");
		List<Business> datalist=businessService.getAllList();
		model.addObject("businessData", datalist);
		List<Template> templatelist=templateService.getAllList();
		model.addObject("templatelist", templatelist);
		List<PublicProvinceCity> list = publicProvinceCityService.getOpenCityListFromRedis();
		
		model.addObject("provincelist", getOpenCityByJiBie(list,1));
		List<PublicProvinceCity> citylistlist =getOpenCityByJiBie(list,2);
		model.addObject("pro_city", getCityStr(citylistlist));
		List<PublicProvinceCity> regionlist =getOpenCityByJiBie(list,3);
		model.addObject("city_region", getCityStr(regionlist));
		return model;
	}
	private String getCityStr(List<PublicProvinceCity> list){
		Map<Integer, StringBuilder> resulMap=new HashMap<Integer, StringBuilder>();
		for (PublicProvinceCity item : list) {
			if (resulMap.containsKey(item.getParentCode())) {
				resulMap.get(item.getParentCode()).append(";"+item.getCode()+"|"+item.getName());
			}else {
				StringBuilder builder=new StringBuilder();
				builder.append(";"+item.getCode()+"|"+item.getName());
				resulMap.put(item.getParentCode(), builder);
			}
		}
		StringBuilder resultBuilder=new StringBuilder();
		for (Map.Entry<Integer, StringBuilder> entry : resulMap.entrySet()) {  
			resultBuilder.append("#"+entry.getKey()+"="+entry.getValue().toString());
		}  

		return resultBuilder.toString();
	}
	private List<PublicProvinceCity> getOpenCityByJiBie(List<PublicProvinceCity> list,int jiBie)
	{
		List<PublicProvinceCity> listnew = new ArrayList<PublicProvinceCity>();
		for (PublicProvinceCity item : list) {
			if (item.getJiBie() == jiBie) {
				listnew.add(item);
			}
		}
		return listnew;
	}
	@RequestMapping("uploadfile")
	@ResponseBody
	public String upLoadFile(HttpServletRequest request,@RequestParam MultipartFile file1){
		String saveName=ParseHelper.ToDateString(new Date());
		return file1.getOriginalFilename()+"#"+saveName;
	}
	@RequestMapping("savetask")
	@ResponseBody
	public int saveTask(HttpServletRequest request,RenRenTask taskItem,String beginDate,String endDate) {
		taskItem.setPusher("");
		taskItem.setStatus(TaskStatus.WaitAudit.value());
		taskItem.setTaskCycle(0d);
		taskItem.setBeginTime(ParseHelper.ToDate(beginDate));
		taskItem.setEndTime(ParseHelper.ToDate(endDate));
		taskItem.setAvailableCount(taskItem.getTaskTotalCount());
		UserContext context=UserContext.getCurrentContext(request);
		taskItem.setCreateName(context.getUserName());
		taskItem.setModifyName(context.getUserName());
		List<Integer> regionCodes=getRegionCodeList(request);
		List<Attachment> attachments=getAttachList(request);
		
		return renRenTaskService.insert(taskItem, regionCodes,attachments);
	}
	private List<Integer> getRegionCodeList(HttpServletRequest request){
		List<Integer> regionCodes=new ArrayList<>();
		Integer provinceCode=ParseHelper.ToInt(request.getParameter("provinceCode"),0);
		if (provinceCode>-1) {
			Long cityCode=ParseHelper.ToLong(request.getParameter("cityCode"),0);
			if (cityCode>-1) {
				String name="";
				Enumeration pNames=request.getParameterNames();
				while(pNames.hasMoreElements()){    
				    name=(String)pNames.nextElement();  
					if (name.indexOf("regionCode")==0) {
						regionCodes.add(ParseHelper.ToInt(request.getParameter(name),0));
					}
				}
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
			String relativePath = PropertyUtils.getProperty("Task_Attach_Path");
			String [] attachList=attachs.split(";");
			for (String fileinfo : attachList) {
				String [] fileNames=fileinfo.split("#");
				Attachment attach=new Attachment();
				attach.setAttachmentName(fileNames[0]);
				attach.setAttachUrl(relativePath+fileNames[1]);
				attachments.add(attach);
			}
		}
		return attachments;
	}
	@RequestMapping("audittask")
	public ModelAndView audiTask() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "任务管理");
		model.addObject("currenttitle", "审核任务");
		model.addObject("viewPath", "taskmanage/audittask");
		List<Business> datalist=businessService.getAllList();
		model.addObject("businessData", datalist);
		List<Template> templatelist=templateService.getAllList();
		model.addObject("templatelist", templatelist);
		return model;
	}
	@RequestMapping("audittaskdo")
	public ModelAndView audiTaskDo(PagedRenRenTaskReq req) {
		ModelAndView model = new ModelAndView("taskmanage/audittaskdo");
		PagedResponse<RenRenTaskModel> resp=renRenTaskService.getPagedRenRenTaskList(req);
		model.addObject("listData", resp);
		return model;
	}

	@RequestMapping("settaskstatus")
	@ResponseBody
	public int setTaskStatus(long taskID,int status) {
		return renRenTaskService.setTaskStatus(taskID, status);
	}
}
