package com.renrentui.renrenadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.resp.ClienterResp;

@Controller
@RequestMapping("clienter")
public class ClienterController {
	@Autowired
	private IClienterService clienterService;
	/**
	* @Des 地推员管理 
	* @Author WangXuDan
	* @Date 2015年9月29日14:45:04
	* @Return
	*/
	@RequestMapping("list")
	public ModelAndView list(){	
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "地推员管理");
		model.addObject("currenttitle", "地推员管理");
		model.addObject("viewPath", "clienter/list");
		return model;
	}
	/**
	* @Des  地推员信息列表
	* @Author WangXuDan
	* @Date 2015年9月29日16:09:13
	* @Return
	*/
	@RequestMapping("listdo")
	public ModelAndView listdo(ClienterReq req) {	
		PagedResponse<ClienterResp> resp =clienterService.queryClienterList(req);
		ModelAndView model = new ModelAndView("clienter/listdo");
		model.addObject("listData", resp);
		return model;
	}

}
