package com.renrentui.renrenadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenapi.service.inter.IAdminToolsService;
import com.renrentui.renrenentity.AccountInfo;

@Controller
@RequestMapping("admintools")
public class AdminToolsController {

	@Autowired
	IAdminToolsService adminTools;

	/**
	 * redis工具
	 * 
	 * @author haichao
	 * @date 2015年9月29日 11:52:01
	 * */
	@RequestMapping("redistools")
	public ModelAndView redisTools() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "welcome");
		model.addObject("currenttitle", "welcome");
		model.addObject("viewPath", "admintools/redistools_do");
		return model;
	}

	/**
	 * redis响应工具
	 * 
	 * @author haichao
	 * @date 2015年9月29日 11:54:26
	 * */
	@RequestMapping("redistoolsdo")
	public String redisToolsDo(HttpServletRequest request,com.renrentui.renrenentity.RedisTools redisTools) {
		return adminTools.getReidsTools(redisTools.getKey(), redisTools.getsType()).toString();
	}
}
