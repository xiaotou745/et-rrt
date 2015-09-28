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
import com.renrentui.api.service.inter.IMenuInfoService;
import com.renrentui.api.service.inter.IRoleAuthService;
import com.renrentui.api.service.inter.IRoleInfoService;
import com.renrentui.entity.MenuInfo;
import com.renrentui.entity.RoleAuth;
import com.renrentui.entity.RoleInfo;
import com.renrentui.entity.req.PagedAccountReq;

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
	public ModelAndView listdo(PagedAccountReq req) {
		ModelAndView model = new ModelAndView("role/listdo");
		List<RoleInfo> datalist=roleInfoService.selectList();
		model.addObject("listData", datalist);
		return model;
	}
	@RequestMapping("add")
	@ResponseBody
	public int add(String roleName) {
		RoleInfo record=new RoleInfo();
		record.setBeLock(false);
		record.setRoleName(roleName);
		return roleInfoService.insert(record);
	}
	@RequestMapping(value = "authlist", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthList(int roleID) {
		List<MenuInfo> menuList = menuInfoService.getRoleAuthSettingList(roleID);
		return MenuHelper.getAuthJson(menuList);
	}

	@RequestMapping("saveauth")
	@ResponseBody
	public String saveauth(int roleID, String newAuth, String oldAuth) {
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
		for (String authid : diffList) {
			RoleAuth authset = new RoleAuth();
			authset.setRoleId(roleID);
			authset.setMenuId(Integer.parseInt(authid));
			authList.add(authset);
		}
		roleAuthService.modifyAuthList(authList);

		return "";
	}

	@RequestMapping("saverole")
	@ResponseBody
	public int saverole(int roleID,int belock,String newName) {
		RoleInfo record=new RoleInfo();
		record.setId(roleID);
		record.setBeLock(belock==1);
		record.setRoleName(newName);
		return roleInfoService.update(record);
	}
}
