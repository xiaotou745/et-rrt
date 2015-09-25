package com.renrentui.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.admin.common.MenuHelper;
import com.renrentui.api.service.inter.IAccountInfoService;
import com.renrentui.api.service.inter.IAccountAuthService;
import com.renrentui.api.service.inter.IMenuInfoService;
import com.renrentui.api.service.inter.IRoleInfoService;
import com.renrentui.common.PagedResponse;
import com.renrentui.common.ResponseBase;
import com.renrentui.core.util.StringUtils;

import com.renrentui.entity.AccountAuth;
import com.renrentui.entity.AccountInfo;

import com.renrentui.entity.MenuInfo;
import com.renrentui.entity.RoleInfo;
import com.renrentui.entity.req.AddNewMenuReq;
import com.renrentui.entity.req.PagedAccountReq;

@Controller
@RequestMapping("authmanage")
public class AuthManageController {
	@Autowired
	private IAccountInfoService accountService;
	@Autowired
	private IMenuInfoService authorityMenuClassService;
	@Autowired
	private IAccountAuthService authorityAccountMenuSetService;
	@Autowired
	private IRoleInfoService authorityRoleService;
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "个人账户权限管理");
		model.addObject("viewPath", "authmanage/list");
		List<RoleInfo> datalist=authorityRoleService.selectList();
		model.addObject("roleData", datalist);
		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAccountReq req) {
		PagedResponse<AccountInfo> resp = accountService.queryAccount(req);
		ModelAndView model = new ModelAndView("authmanage/listdo");
		model.addObject("listData", resp);
		return model;
	}
	@RequestMapping("getroleid")
	@ResponseBody
	public int getRoleID(int userID) {
		AccountInfo userAccount = accountService.getByID(userID);
		if (userAccount!=null) {
			return userAccount.getRoleid();
		}
		return -1;
	}
	@RequestMapping("updateroleid")
	@ResponseBody
	public int updateRoleID(int userID,int newRoleID) {
		return accountService.updateRoleID(userID, newRoleID);
	}
	@RequestMapping(value = "authlist", produces= "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthList(int userID) {
		List<MenuInfo> menuList = authorityMenuClassService.getAuthSettingList(userID);
		return MenuHelper.getAuthJson(menuList);
	}

	@RequestMapping("saveauth")
	@ResponseBody
	public String saveauth(int userID, String newAuth, String oldAuth) {
		List<String> newList = new ArrayList<>();
		List<String> oldList = new ArrayList<>();
		List<String> diffList = new ArrayList<>();
		if (newAuth != null && !newAuth.isEmpty()) {
			Collections.addAll(newList, newAuth.split(","));
			Collections.addAll(diffList, newAuth.split(","));
		}
		if (oldAuth != null && !oldAuth.isEmpty()) {
			Collections.addAll(oldList, oldAuth.split(","));
		}

		diffList.removeAll(oldList);// diffList中剩余的是新增的权限id
		oldList.removeAll(newList);// oldList中剩余的是被删掉的权限id
		diffList.addAll(oldList);// diffList中剩余的是发生了变更的权限id
		if (diffList.size() == 0) {
			return "没有任何修改，不需要保存";
		}
		List<AccountAuth> authList = new ArrayList<>();
		for (String authid : diffList) {
			AccountAuth authset = new AccountAuth();
			authset.setAccoutid(userID);
			authset.setMenuid(Integer.parseInt(authid));
			authList.add(authset);
		}
		authorityAccountMenuSetService.modifyAuthList(authList);

		return "";
	}

	@RequestMapping("menulist")
	public ModelAndView menuList(Integer parId) {
		parId = (parId == null ? 0 : parId);
		List<MenuInfo> resp = authorityMenuClassService.getListMenuByParId(parId);
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "权限");
		view.addObject("currenttitle", "菜单管理");
		view.addObject("viewPath", "authmanage/menulist");
		view.addObject("listData", resp);
		view.addObject("currentMenu", authorityMenuClassService.getMenuById(parId));
		return view;
	}
	
	@RequestMapping("addnewmenu")
	@ResponseBody
	public ResponseBase addNewMenu(MenuInfo req){
		ResponseBase resp = new ResponseBase();
		if(StringUtils.isEmpty(req.getMenuname())){
			resp.setMessage("请填写菜单名称");
		}else{
			int curId = req.getParid() == null ? 0 : req.getParid();
			req.setParid(curId);
			req.setBelock(false);
			authorityMenuClassService.addMenu(req);
			
			resp.setMessage("添加菜单成功");
			resp.setResponseCode(1);
		}
		
		
		return resp;
	}
}
