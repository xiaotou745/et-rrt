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
import com.renrentui.renrenapi.service.inter.IMenuInfoService;
import com.renrentui.renrenapi.service.inter.IRoleInfoService;
import com.renrentui.renrencore.cache.redis.RedisService;
import com.renrentui.renrencore.security.MD5Util;
import com.renrentui.renrencore.util.CookieUtils;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.MenuInfo;
import com.renrentui.renrenentity.RoleInfo;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.SimpleUserInfoModel;
import com.renrentui.renrenentity.domain.UpdatePwdReq;
import com.renrentui.renrenentity.req.PagedAccountInfoReq;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private RedisService redisService;
	@Autowired
	private IAccountInfoService accountInfoService;
	@Autowired
	private IAccountAuthService accountAuthService;
	@Autowired
	private IMenuInfoService menuService;

	@Autowired
	private IRoleInfoService authorityRoleService;
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "用户设置");
		view.addObject("currenttitle", "用户管理");
		view.addObject("viewPath", "account/list");
		List<RoleInfo> datalist=authorityRoleService.selectList();
		view.addObject("roleData", datalist);
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
	@RequestMapping("adduser")
	@ResponseBody
	public int addUser(HttpServletRequest request,AccountInfo account) {
		if (account==null||
				account.getLoginName()==null||
				account.getLoginName().isEmpty()||
				account.getUserName()==null||
				account.getUserName().isEmpty()
				) {
			return -1;
		}
		account.setAccountType(0);
		account.setRoleId(0);
		String password = MD5Util.MD5(account.getPassWord());
		account.setPassWord(password);
		return accountInfoService.insert(account);
	}

	@RequestMapping("updateuser")
	@ResponseBody
	public int updateUser(HttpServletRequest request,AccountInfo account) {
		if (account.getPassWord()!=null&&!account.getPassWord().isEmpty()) {
			String password = MD5Util.MD5(account.getPassWord());
			account.setPassWord(password);
		}
		return accountInfoService.update(account);
	}
	@RequestMapping("getuserinfo")
	@ResponseBody
	public AccountInfo getUserInfo(HttpServletRequest request,int userId) {
		return accountInfoService.getByID(userId);
	}
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String username, @RequestParam String password, @RequestParam String code,
			 Integer rememberMe) throws ServletException, IOException {
		String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
		Date loginTime = new Date();
		String sessionCode = LoginHelper.getAuthCode(request,LoginUtil.ADMIN_JSESSIONID);
		//一次性验证码,防止暴力破解
		LoginHelper.removeAuthCodeCookie(request, response,LoginUtil.ADMIN_JSESSIONID);
		// 如果已登录,直接返回
		boolean isLogin = LoginUtil.checkIsLogin(request,response);
		// 如果已登录,直接返回已登录
		if (isLogin) {
			response.sendRedirect(basePath+"/ordermanage/auditorder");
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
		CookieUtils.setCookie(request,response,LoginUtil.LOGIN_COOKIE_NAME, JsonUtil.obj2string(loginUser), cookieMaxAge,
				true);
		response.sendRedirect(basePath+"/ordermanage/auditorder");
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
		CookieUtils.deleteCookie(request, response,LoginUtil.LOGIN_COOKIE_NAME);
		response.sendRedirect(PropertyUtils.getProperty("java.renrenadmin.url") + "/");

	}
	@RequestMapping(value = "changepwd")
	public ModelAndView changePwd(){
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "修改密码");
		view.addObject("viewPath", "account/changepwd");
		return view;
	}
	@RequestMapping(value = "updatepwd")
	@ResponseBody
	public int updatePwd(HttpServletRequest request, UpdatePwdReq req){
		UserContext context = UserContext.getCurrentContext(request);
		req.setUserId(context.getId());
		return accountInfoService.updatePwd(req);
	}
}
