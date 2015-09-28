package com.renrentui.admin.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.admin.common.LoginUtil;
import com.renrentui.admin.common.UserContext;
import com.renrentui.api.common.LoginHelper;
import com.renrentui.api.service.inter.IAccountAuthService;
import com.renrentui.api.service.inter.IAccountInfoService;
import com.renrentui.common.PagedResponse;
import com.renrentui.core.cache.redis.RedisService;
import com.renrentui.core.util.CookieUtils;
import com.renrentui.core.util.JsonUtil;
import com.renrentui.core.util.ParseHelper;
import com.renrentui.core.util.PropertyUtils;
import com.renrentui.entity.AccountInfo;
import com.renrentui.entity.domain.SimpleUserInfoModel;
import com.renrentui.entity.req.PagedAccountInfoReq;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private RedisService redisService;
	@Autowired
	private IAccountInfoService accountInfoService;
	@Autowired
	private IAccountAuthService accountAuthService;

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "用户设置");
		view.addObject("currenttitle", "用户管理");
		view.addObject("viewPath", "account/list");
		return view;
	}

	@RequestMapping("listdo")
	public ModelAndView list(PagedAccountInfoReq req) {
		PagedResponse<AccountInfo> resp = accountInfoService.queryAccount(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "account/listdo");
		view.addObject("listData", resp);
		return view;
	}
	
	@RequestMapping("code")
	public ModelAndView code(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("account/code");
		return mv;
	}
	
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String username, @RequestParam String password, @RequestParam String code,
			 Integer rememberMe) throws ServletException, IOException {
		String basePath = PropertyUtils.getProperty("java.admin.url");
		Date loginTime = new Date();
		String sessionCode = LoginHelper.getAuthCode(request,LoginUtil.ADMIN_JSESSIONID);
		//一次性验证码,防止暴力破解
		LoginHelper.removeAuthCodeCookie(request, response,"admin",LoginUtil.ADMIN_JSESSIONID);
		// 如果已登录,直接返回
		boolean isLogin = LoginUtil.checkIsLogin(request,response);
		// 如果已登录,直接返回已登录
		if (isLogin) {
			response.sendRedirect(basePath+"/order/list");
			return;
		}

		String error = "";
		AccountInfo account = null;
		// 验证码不正确
		if (sessionCode == null || !sessionCode.toString().toLowerCase().equals(code.toLowerCase())) {
			error = "验证码不正确";
		}else{
			account = accountInfoService.login(username, password);
			if (account == null) {
				error = "用户名或密码错误";
			}
			else if(account.getStatus() != 1){
				error = "您的账号已经被禁用,请联系管理员";
			}
		}
		if(!error.equals("")){
			request.setAttribute("error", error);
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// 登录成功,写cookie
		int cookieMaxAge = -1;
		// 选择记住我,默认cookie24小时,否则随浏览器的关闭而失效
		boolean isRemeberMe = rememberMe!= null && rememberMe.equals(1);
		if (isRemeberMe) {
			cookieMaxAge = 7 * 60 * 60 * 24;
		}
		account.setPassWord(password);//这个是为了给cookie准备的参数
		error = "成功";

		SimpleUserInfoModel loginUser = new SimpleUserInfoModel();
		loginUser.setAccountType(ParseHelper.ToInt(account.getAccountType(), 1));
		loginUser.setId(account.getId());
		loginUser.setLoginName(account.getLoginName());
		loginUser.setPassword("");
		loginUser.setRoleId(account.getRoleId());
		loginUser.setUserName(account.getUserName());
		CookieUtils.setCookie(request,response,"admin", LoginUtil.LOGIN_COOKIE_NAME, JsonUtil.obj2string(loginUser), cookieMaxAge,
				true);
		response.sendRedirect(basePath+"/order/list");
	}
	
	/**
	 * 注销
	 * 
	 * @author pengyi
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "logoff")
	public void logoff(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 删除登录cookie
		CookieUtils.deleteCookie(request, response,"admin", LoginUtil.LOGIN_COOKIE_NAME);
		response.sendRedirect(PropertyUtils.getProperty("java.renrenadmin.url") + "/");

	}
}
