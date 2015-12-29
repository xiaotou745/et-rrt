package com.renrentui.renrenadmin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenentity.RoleInfo;
/**
 * 
 * 分佣管理
 * @author ofmyi_000
 *
 */
@Controller
@RequestMapping("subcommission")
public class SubCommissionController {
	@RequestMapping("add")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "分佣管理");
		view.addObject("currenttitle", "添加分佣策略");
		view.addObject("viewPath", "subcommission/add");
		return view;
	}
}
