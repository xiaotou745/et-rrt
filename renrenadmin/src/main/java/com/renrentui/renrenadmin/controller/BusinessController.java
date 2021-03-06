package com.renrentui.renrenadmin.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IBusinessService;
import com.renrentui.renrencore.util.ImageHelper;
import com.renrentui.renrencore.util.StreamUtils;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.BusinessModel;
import com.renrentui.renrenentity.req.BusinessBalanceReq;
import com.renrentui.renrenentity.req.PagedBusinessReq;


@Controller
@RequestMapping("business")
public class BusinessController {
	@Autowired
	private IBusinessService businessService;		


	/**
	 * 商户列表管理页面 
	 * @author hulignbo
	 * @Date 2015年9月29日 11:17:28
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "商户");
		model.addObject("currenttitle", "商户管理");	
		model.addObject("viewPath", "business/list");
		return model;
	}	
	
	/**
	 * 商户列表管理页面 
	 * @author hulignbo
	 * @Date 2015年9月29日 11:17:53
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedBusinessReq req)  {			
		
		PagedResponse<BusinessModel> resp = businessService.getBusinessList(req);
		ModelAndView model = new ModelAndView("business/listdo");		
		model.addObject("listData", resp);
		return model;		
	}		
	
	/**
	 * 添加商户 
	 * @author hulignbo
	 * @Date 2015年9月30日 15:35:12
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("addbusiness")
	@ResponseBody
	public int addBusiness(Business record) {
	
		return businessService.add(record);
	}
	
	/**
	 * 添加商户 
	 * @author hulignbo
	 * @Date 2015年9月30日 15:35:12
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("modifybusiness")
	@ResponseBody
	public int modifyBusiness(Business record) {
	
		return businessService.modify(record);
	}
	
	
	/**
	 * 商户充值
	 * @author hulingbo	
	 * @Date 2015年9月30日 15:35:51
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("addbusinessdelta")
	@ResponseBody
	public int addBusinessDelta(HttpServletRequest request,BusinessBalanceReq req) {
	
		UserContext context=UserContext.getCurrentContext(request);		
		String userName=context.getUserName();
		return businessService.addBalance(req,userName);
	}	
	
	/**
	 * Logo上传
	 * @author hulingbo	
	 * @Date 2015年10月9日 14:10:25
	 * @param search 查询条件实体
	 * @return	
	 * @throws IOException 
	 * @throws ServletException 
	 */	
	@RequestMapping("uploadfile")
	@ResponseBody
	public int uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	 byte[] byteArrary=	StreamUtils.copyToByteArray( request.getInputStream());
		businessService.UploadFile(byteArrary, "1");
		ImageHelper.UploadImg(request,"business");
		return 1;
	}	

	
}
