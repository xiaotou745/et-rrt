package com.renrentui.renrenadmin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.LoginUtil;
import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.common.LoginHelper;
import com.renrentui.renrenapi.service.inter.IAccountAuthService;
import com.renrentui.renrenapi.service.inter.IAccountInfoService;
import com.renrentui.renrenapi.service.inter.IBusinessBalanceRecordService;
import com.renrentui.renrenapi.service.inter.IBusinessBalanceService;
import com.renrentui.renrenapi.service.inter.IBusinessService;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrenapi.service.inter.IMenuInfoService;
import com.renrentui.renrenapi.service.inter.IRoleInfoService;
import com.renrentui.renrencore.cache.redis.RedisService;
import com.renrentui.renrencore.security.MD5Util;
import com.renrentui.renrencore.util.CookieUtils;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.BusinessBalanceRecord;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.MenuInfo;
import com.renrentui.renrenentity.RoleInfo;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.domain.SimpleUserInfoModel;
import com.renrentui.renrenentity.domain.UpdatePwdReq;
import com.renrentui.renrenentity.req.BusinessBalanceReq;
import com.renrentui.renrenentity.req.PagedAccountInfoReq;
import com.renrentui.renrenentity.req.PagedBusinessReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;

@Controller
@RequestMapping("clienterwithdraw")
public class ClienterWithdrawFormController {
	@Autowired
	private IClienterWithdrawFormService clienterWithdrawFormService;		


	/**
	 * 用户提现列表管理页面 
	 * @author hulignbo
	 * @Date 2015年9月30日 15:38:27
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "账务管理");
		model.addObject("currenttitle", "提现管理");	
		model.addObject("viewPath", "clienterwithdraw/list");
		return model;
	}	
	
	/**
	 * 用户提现列表管理页面 
	 * @author hulignbo
	 * @Date 2015年9月30日 15:38:58
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedClienterWithdrawFormReq req)  {			
		
		PagedResponse<ClienterWithdrawFormDM> resp = clienterWithdrawFormService.getList(req);
		ModelAndView model = new ModelAndView("clienterwithdraw/listdo");		
		model.addObject("listData", resp);
		return model;		
	}		

	/**
	 * 审核通过
	 * @author hulingbo	
	 * @Date 2015年9月30日 17:35:51
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("auditpass")
	@ResponseBody
	public int auditpass(HttpServletRequest request,int  withwardId) {
	
		ClienterWithdrawForm record=new ClienterWithdrawForm();
		record.setId((long)withwardId);
		
		UserContext context=UserContext.getCurrentContext(request);
		record.setAuditName(context.getUserName());
		return clienterWithdrawFormService.AuditPass(record);			
	}	
	
	/**
	 * 审核拒绝
	 * @author hulingbo	
	 * @Date 2015年10月8日 14:40:33
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("auditrefuse")
	@ResponseBody
	public int auditrefuse(HttpServletRequest request,int  withwardId) {
	
		ClienterWithdrawForm record=new ClienterWithdrawForm();
		record.setId((long)withwardId);
		UserContext context=UserContext.getCurrentContext(request);
		record.setAuditName(context.getUserName());
		return clienterWithdrawFormService.AuditRefuse(record);	
	}	
}
