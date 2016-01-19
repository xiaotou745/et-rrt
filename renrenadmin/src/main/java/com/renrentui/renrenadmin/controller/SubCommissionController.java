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
import com.renrentui.renrenentity.Strategy;
import com.renrentui.renrenentity.StrategyChild;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.RenRenTaskModel;
import com.renrentui.renrenentity.req.PagedRenRenTaskReq;
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
	public double saveTask(HttpServletRequest request,String data) {
		UserContext context=UserContext.getCurrentContext(request);
		StrategyModelReq req=JsonUtil.str2obj(data,StrategyModelReq.class);
		req.setOptName(context.getUserName());
		return subCommissionService.addStrategy(req);
	}
	/**
	 * 分佣策略列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "分佣管理");
		view.addObject("currenttitle", "分佣策略列表");
		view.addObject("viewPath", "subcommission/list");
		return view;
	}
	/**
	 * 分佣策略列表 异步不分页()
	 * @param req
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView audiTaskDo(Strategy req) {
		ModelAndView model = new ModelAndView("subcommission/listdo");
		List<Strategy>resp=subCommissionService.getStrategyList(req);
		model.addObject("list", resp);
		return model;
	}
	/**
	 * 变更状态
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping("updateStatus")
	@ResponseBody
	public int updateStatus(HttpServletRequest request,Strategy req) {
		UserContext context=UserContext.getCurrentContext(request);
		req.setOptName(context.getUserName());
		return subCommissionService.updateStatus(req);
	}
	/**
	 * 分佣策略详情
	 * @param req
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(Long id) {
		ModelAndView model = new ModelAndView("adminView");
		Strategy  strategy=subCommissionService.getStrategyById(id);
		List<StrategyChild> childs=subCommissionService.getStrategyChildById(id);
		model.addObject("strategy", strategy);
		model.addObject("childs", childs);
		model.addObject("subtitle", "分佣管理");
		model.addObject("currenttitle", "分佣策略详情");
		model.addObject("viewPath", "subcommission/detail");
		return model;
	}
}
