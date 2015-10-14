package com.renrentui.renrenadmin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.dao.impl.RenRenTaskDao;
import com.renrentui.renrenapi.service.inter.IBusinessService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrenapi.service.inter.ITemplateService;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrencore.enums.TemplateStatus;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.Template;
import com.renrentui.renrenentity.TemplateDetail;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.PageTemplateModel;
import com.renrentui.renrenentity.domain.RenRenTaskDetail;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
import com.renrentui.renrenentity.req.PagedTemplateReq;
import com.renrentui.renrenentity.req.UpdateStatusReq;


@Controller
@RequestMapping("templatemanage")
public class TemplateController {
	@Autowired
	private ITemplateService templateService;
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IRenRenTaskService renRenTaskService;
	@RequestMapping("newtemplate")
	public ModelAndView newTemplate() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "模板管理");
		model.addObject("currenttitle", "新建模板");
		model.addObject("viewPath", "templatemanage/newtemplate");
		List<Business> datalist=businessService.getAllList();
		model.addObject("businessData", datalist);
		return model;
	}

	@RequestMapping("savetemplate")
	@ResponseBody
	public int saveTemplate(HttpServletRequest request,String tempName,String tempRemark,Long businessId,String child) {
		if (tempName==null||tempName.isEmpty()||
			tempRemark==null||tempRemark.isEmpty()||
			businessId==null||businessId<=0||
			child==null||child.isEmpty()) {
			return -1;
		}
		TemplateModel model=getTemplateModel(request,null,tempName,tempRemark,businessId,child);
		return templateService.insert(model);
	}
	@RequestMapping("audittemplate")
	public ModelAndView auditTemplate() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "模板管理");
		model.addObject("currenttitle", "审核模板");
		model.addObject("viewPath", "templatemanage/audittemplate");
		List<Business> datalist=businessService.getAllList();
		model.addObject("businessData", datalist);
		return model;
	}

	@RequestMapping("audittemplatedo")
	public ModelAndView auditTemplateDo(PagedTemplateReq req) {
		ModelAndView model = new ModelAndView("templatemanage/audittemplatedo");
		PagedResponse<PageTemplateModel> resp=templateService.queryTemplate(req);
		model.addObject("listData", resp);
		return model;
	}

	@RequestMapping("settemplatestatus")
	@ResponseBody
	public int setTemplateStatus(HttpServletRequest request,UpdateStatusReq req) {
		UserContext context=UserContext.getCurrentContext(request);
		req.setUserName(context.getUserName());
		return templateService.setTemplateStatus(req);
	}
	@RequestMapping("detail")
	public ModelAndView templateDetail(Long templateId) {
		if (templateId==null||templateId<0) {
			throw new RuntimeException("templateId不能为空");
		}
		TemplateModel templateInfo=templateService.detail(templateId);
		if (templateInfo==null) {
			throw new RuntimeException("id="+templateId+"的模板不存在");
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "模板管理");
		model.addObject("currenttitle", "修改模板");
		model.addObject("viewPath", "templatemanage/detail");
		model.addObject("templateInfo", templateInfo);
		List<Business> datalist=businessService.getAllList();
		model.addObject("businessData", datalist);
		return model;
	}
	@RequestMapping(value = "/updatetemplate", produces = {"application/text;charset=UTF-8"})
	@ResponseBody
	public String updateTemplate(HttpServletRequest request,Long id,String tempName,String tempRemark,Long businessId,String child) {
		if (tempName==null||tempName.isEmpty()||
				tempRemark==null||tempRemark.isEmpty()||
				businessId==null||businessId<=0||
				child==null||child.isEmpty()) {
				return "-1";
			}
		TemplateModel model=getTemplateModel(request,id,tempName,tempRemark,businessId,child);
		model.setCreateName("");
		Integer result= templateService.update(model);
		if (result>0) {
			List<RenRenTask> taskList=renRenTaskService.getListByTemplateId(id);
			if (taskList!=null&&taskList.size()>0) {
				StringBuilder sb=new StringBuilder();
				for (RenRenTask renRenTask : taskList) {
					if (!sb.toString().isEmpty()) {
						sb.append("\n");
					}
					sb.append(renRenTask.getTaskTitle());
				}
				return sb.toString();
			}
		}
		return result.toString();
	}
	private TemplateModel getTemplateModel(HttpServletRequest request,Long id,String tempName,String tempRemark,Long businessId,String child){
		UserContext context = UserContext.getCurrentContext(request);
		
		TemplateModel record=new TemplateModel();
		record.setId(id);
		record.setBusinessId(businessId);
		record.setLastOptName(context.getUserName());
		record.setCreateName(context.getUserName());
		record.setRemark(tempRemark);
		record.setStatus((short)TemplateStatus.Valid.value());
		record.setTemplateName(tempName);
		List<TemplateDetail> detailList=new ArrayList<TemplateDetail> ();
		String[] childs=child.split("#");
		for (String item : childs) {
			if (item.isEmpty()) {
				continue;
			}
			TemplateDetail detail=new TemplateDetail();
			detail.setTemplateId(id);
			detail.setControlData("");
			detail.setDefaultValue("");
			String[] childItem=item.split(";");
			for (String childvalue : childItem) {
				String[] values=childvalue.split("=");
				String detailValue="";
				if (values.length==2) {
					detailValue=values[1];
				}
				switch (values[0]) {
				case "ordernum":
					detail.setOrderNum(ParseHelper.ToInt(detailValue, 0));
					detail.setName("key"+detailValue);
					break;
				case "controlid":
					detail.setControlId(ParseHelper.ToLong(detailValue, 0));
					break;
				case "title":
					detail.setTitle(detailValue);
					break;
				case "name":
					detail.setName(detailValue);
					break;
				case "defaultvalue":
					detail.setDefaultValue(detailValue);
					break;
				default:
					break;
				}
			}
			detailList.add(detail);
		}
		record.setDetailList(detailList);
		return record;
	}
}
