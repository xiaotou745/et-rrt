package com.renrentui.renrenadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenapi.service.inter.IActivityService;
import com.renrentui.renrenapi.service.inter.IClienterWxService;
import com.renrentui.renrenentity.Activity;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWxModel;
import com.renrentui.renrenentity.req.PagedClienterWxReq;
import com.renrentui.renrenentity.req.UpdateActivityReq;

@Controller
@RequestMapping("weixin")
public class WeiXinController {
	@Autowired
	private IActivityService iActivityService;
	
	@Autowired
	private IClienterWxService clienterWxService;

	@RequestMapping("attentionuserlist")
	public ModelAndView attentionUserList() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "微信平台管理");
		view.addObject("currenttitle", "微信公众号用户管理");
		view.addObject("viewPath", "weixin/attentionuserlist");
		return view;
	}

	@RequestMapping("attentionuserlistdo")
	public ModelAndView attentionUserListDo(PagedClienterWxReq req) {
		ModelAndView view = new ModelAndView("weixin/attentionuserlistdo");
		PagedResponse<ClienterWxModel> modelPagedResponse = new PagedResponse<ClienterWxModel>();
		 
		modelPagedResponse = clienterWxService.getlist(req);
		view.addObject("listdata", modelPagedResponse);
		return view;
	}

	/*
	 * 活动管理 wangchao
	 */
	@RequestMapping("activitymanage")
	public ModelAndView activityManage() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "微信平台管理");
		view.addObject("currenttitle", "活动管理");
		view.addObject("viewPath", "weixin/activitymanage");
		List<Activity> list = iActivityService.getList();
		view.addObject("listData", list);
		return view;
	}

	@RequestMapping("shutupactivity")
	@ResponseBody
	public boolean shutupactivity(UpdateActivityReq req) {
		return iActivityService.shutUpActivity(req) > 0;
	}

	@RequestMapping("startupactivity")
	@ResponseBody
	public boolean startupactivity(UpdateActivityReq req) {
		return iActivityService.startUpActivity(req) > 0;
	}

	@RequestMapping("activitydetail")
	public ModelAndView activitydetail(UpdateActivityReq req) {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "微信平台管理");
		view.addObject("currenttitle", "活动详情");
		view.addObject("viewPath", "weixin/activitydetail");
		// List<Activity> list= iActivityService.getActivityDetail();
		// view.addObject("listData", list);
		return view;
	}
}
