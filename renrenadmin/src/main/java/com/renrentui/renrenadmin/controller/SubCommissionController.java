package com.renrentui.renrenadmin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.ISubCommissionService;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrenentity.req.StrategyModelReq;
/**
 * 
 * 分佣管理
 * @author ofmyi_000
 *
 */
@Controller
@RequestMapping("subcommission")
public class SubCommissionController {
	@Autowired
	private ISubCommissionService subCommissionService;
	/**
	 * 分佣策略新建页面
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "分佣管理");
		view.addObject("currenttitle", "添加分佣策略");
		view.addObject("viewPath", "subcommission/add");
		return view;
	}
	/**
	 * 分佣策略保存
	 * @param request
	 * @param data
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public int saveTask(HttpServletRequest request,String data) {
		UserContext context=UserContext.getCurrentContext(request);
		StrategyModelReq req=JsonUtil.str2obj(data,StrategyModelReq.class);
		req.setOptName(context.getUserName());
		return subCommissionService.addStrategy(req);
	}
}
