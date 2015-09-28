package com.renrentui.renrenadmin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.MenuHelper;
import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IMenuInfoService;
import com.renrentui.renrenapi.service.inter.IRoleAuthService;
import com.renrentui.renrenapi.service.inter.IRoleInfoService;
import com.renrentui.renrenentity.MenuInfo;
import com.renrentui.renrenentity.RoleAuth;
import com.renrentui.renrenentity.RoleInfo;
import com.renrentui.renrenentity.domain.MenuEntity;
import com.renrentui.renrenentity.req.PagedAccountInfoReq;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private IMenuInfoService menuInfoService;
	@Autowired
	private IRoleInfoService roleInfoService;
	@Autowired
	private IRoleAuthService roleAuthService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "角色管理");
		model.addObject("viewPath", "role/list");
		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAccountInfoReq req) {
		ModelAndView model = new ModelAndView("role/listdo");
		List<RoleInfo> datalist=roleInfoService.selectList();
		model.addObject("listData", datalist);
		return model;
	}
	@RequestMapping("add")
	@ResponseBody
	public int add(HttpServletRequest request,String roleName) {
		UserContext context = UserContext.getCurrentContext(request);
		RoleInfo record=new RoleInfo();
		record.setBeLock(false);
		record.setRemark("");
		record.setOptName(context.getUserName());
		record.setRoleName(roleName);
		return roleInfoService.insert(record);
	}
	@RequestMapping(value = "authlist", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthList(int roleID) {
		List<MenuEntity> menuList = menuInfoService.getMenuListByRoleID(roleID);
		return MenuHelper.getAuthJson(menuList);
	}

	@RequestMapping("saveauth")
	@ResponseBody
	public String saveauth(HttpServletRequest request,int roleID, String newAuth, String oldAuth) {
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
		List<RoleAuth> authList = new ArrayList<>();
		UserContext context = UserContext.getCurrentContext(request);
		for (String authid : diffList) {
			RoleAuth authset = new RoleAuth();
			authset.setRoleId(roleID);
			authset.setOptName(context.getUserName());
			authset.setMenuId(Integer.parseInt(authid));
			authList.add(authset);
		}
		roleAuthService.modifyAuthList(authList);

		return "";
	}

	@RequestMapping("saverole")
	@ResponseBody
	public int saverole(HttpServletRequest request,int roleID,int belock,String newName) {
		UserContext context = UserContext.getCurrentContext(request);
		RoleInfo record=new RoleInfo();
		record.setId(roleID);
		record.setOptName(context.getUserName());
		record.setBeLock(belock==1);
		record.setRoleName(newName);
		return roleInfoService.update(record);
	}
}
