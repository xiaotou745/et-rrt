package com.renrentui.renrenadmin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;  

import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService; 
import com.renrentui.renrenentity.domain.OpenCityModel;
import com.renrentui.renrenentity.req.HotAndPublicCityReq;

@Controller
@RequestMapping("region")
public class RegionController {
	@Autowired
	private IPublicProvinceCityService iPublicProvinceCityService;
	@RequestMapping("hotandpubliccity")
	public ModelAndView hotAndPublicCity(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "城市管理");
		model.addObject("currenttitle", "城市管理");
		model.addObject("viewPath", "region/hotandpubliccity");
		return model;
	}
	
	@RequestMapping("listdo")
	public ModelAndView listdo(HotAndPublicCityReq hotAndPublicCityReq) {
		List<OpenCityModel> citys=new ArrayList<OpenCityModel>();
		String cityname = hotAndPublicCityReq.getCityName();
	    if (cityname!=null&&!cityname.isEmpty()) {
		   citys=  iPublicProvinceCityService.getOpenCityList(hotAndPublicCityReq);
		} 
		ModelAndView model = new ModelAndView("region/listdo"); 
		model.addObject("listData", citys);
		return model; 
	}
	
}
