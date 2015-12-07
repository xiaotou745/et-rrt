package com.renrentui.renrenadmin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IAdminToolsService;
import com.renrentui.renrenapi.service.inter.IAppVersionService;
import com.renrentui.renrenentity.AppVersion;
import com.renrentui.renrenentity.common.PagedRequestBase;
import com.renrentui.renrenentity.common.PagedResponse;

@Controller
@RequestMapping("admintools")
public class AdminToolsController {

	@Autowired
	IAdminToolsService adminToolsService;
	@Autowired
	RedisService redisService;
	
	@Autowired
	IAppVersionService appVersionService;
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
	/**
	 * app版本控制
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("appversion")
	public ModelAndView appversion() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "版本控制");
		view.addObject("viewPath", "admintools/appversion");
		appVersionService.modify();
		return view;
	}
	@RequestMapping("appversiondo")
	public ModelAndView appversiondo(PagedRequestBase req) {
		ModelAndView view = new ModelAndView("admintools/appversiondo");
		PagedResponse<AppVersion> resp=appVersionService.queryAppVersion(req);
		view.addObject("listData", resp);
		return view;
	}

	@RequestMapping("cancelversionpublish")
	@ResponseBody
	public int cancelVersionPublish(int id, HttpServletRequest request) {
		UserContext context = UserContext.getCurrentContext(request);
		return appVersionService.cancel(id, context.getUserName());
	}

	@RequestMapping("getversionbyid")
	@ResponseBody
	public AppVersion getVersionById(int id, HttpServletRequest request) {
		return appVersionService.getByID(id);
	}

	@RequestMapping("saveversion")
	@ResponseBody
	public int saveVersion(AppVersion version, int opType,
			HttpServletRequest request) {
		UserContext context = UserContext.getCurrentContext(request);
		version.setCreateBy(context.getUserName());
		version.setUpdateBy(context.getUserName());

		if (opType == 3) {
			if (version.getIsTiming() == 1) {
				version.setPubStatus(0);
			} else {
				version.setPubStatus(1);
				version.setTimingDate(new Date());
			}
			return appVersionService.insert(version);

		} else {
			return appVersionService.update(version);
		}
	}
}

