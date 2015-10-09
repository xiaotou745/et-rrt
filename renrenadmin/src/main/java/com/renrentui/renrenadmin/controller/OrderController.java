package com.renrentui.renrenadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenapi.service.inter.IBusinessService;

@Controller
@RequestMapping("ordermanage")
public class OrderController {
	@Autowired
	private IBusinessService businessService;
	/**
	 * 商户列表管理页面 
	 * @author hulignbo
	 * @Date 2015年9月29日 11:17:28
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("auditorder")
	public ModelAndView list(){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "订单管理");
		model.addObject("currenttitle", "订单审核");	
		model.addObject("viewPath", "ordermanage/auditorder");
		return model;
	}	
}
