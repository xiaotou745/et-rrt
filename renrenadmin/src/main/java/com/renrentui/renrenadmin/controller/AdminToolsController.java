package com.renrentui.renrenadmin.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenapi.service.inter.IAdminToolsService;
import com.renrentui.renrencore.cache.redis.RedisService;

@Controller
@RequestMapping("admintools")
public class AdminToolsController {

	@Autowired
	IAdminToolsService adminToolsService;
	@Autowired
	RedisService redisService;
	/**
	 * redis工具
	 * 
	 * @author haichao
	 * @date 2015年9月29日 11:52:01
	 * */
	@RequestMapping("redistools")
	public ModelAndView redisTools() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "缓存管理");
		model.addObject("currenttitle", "缓存管理");
		model.addObject("viewPath", "admintools/redistools");
		return model;
	}

	/**
	 * redis响应工具
	 * 
	 * @author haichao
	 * @date 2015年9月29日 11:54:26
	 * */
	@RequestMapping("redistools_do")
	@ResponseBody
	public String redisToolsDo(HttpServletRequest request,com.renrentui.renrenentity.RedisTools redisTools) {
		Set<String> set=adminToolsService.getReidsTools(redisTools.getKey(), redisTools.getsType());
		return set.toString().replace("[", "").replace("]","");
	}
}

