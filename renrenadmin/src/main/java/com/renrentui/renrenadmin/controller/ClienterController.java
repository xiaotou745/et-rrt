package com.renrentui.renrenadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ModifyClienterStatusReq;
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
	/**
	* @Des 修改用户状态 
	* @Author WangXuDan
	* @Date 2015年10月8日11:43:44
	* @Return
	*/
	@RequestMapping(value="editclienterstatus",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase editclienterstatus(ModifyClienterStatusReq req,HttpServletRequest request){
		ResponseBase response = new ResponseBase();
		req.setLastOptName(UserContext.getCurrentContext(request).getLoginName());
		response =clienterService.editClienterStatus(req);
		return response;
	}
	/**
	 * 地推员交易列表
	 * @return
	 */
	@RequestMapping("recordlist")
	public ModelAndView recordlist(String phoneNo,String name,Double blance,Double hadWithdraw,Long id){	
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "地推员管理");
		model.addObject("currenttitle", "地推员交易列表");
		model.addObject("viewPath", "clienter/recordlist");
		model.addObject("phoneNo", phoneNo);
		model.addObject("name", name);
		model.addObject("blance", blance);
		model.addObject("hadWithdraw", hadWithdraw);
		model.addObject("id", id);
		return model;
	}

}
