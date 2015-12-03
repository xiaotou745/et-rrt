package com.renrentui.renrenadmin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("region")
public class RegionController {
	
	@RequestMapping("hotandpubliccity")
	public ModelAndView hotAndPublicCity(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "城市管理");
		model.addObject("currenttitle", "城市管理");
		model.addObject("viewPath", "region/hotandpubliccity");
		return model;
	}
}
