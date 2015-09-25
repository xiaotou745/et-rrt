package com.renrentui.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.renrentui.api.dao.inter.IMenuInfoDao;
import com.renrentui.api.service.inter.IMenuInfoService;
import com.renrentui.entity.MenuInfo;
import com.renrentui.entity.req.AddNewMenuReq;

@Service
public class MenuInfoService implements IMenuInfoService {
	@Autowired
	private IMenuInfoDao dao;
	
	@Override
	public List<MenuInfo> getMenuListByUserID(int userID) {
		return dao.getMenuListByUserID(userID);
	}

	@Override
	public boolean checkHasAuth(int userID,int menuID) {
		List<MenuInfo> data=dao.getMenuListByUserID(userID);
		if (data!=null&&data.size()>0) {
			for (MenuInfo menuEntity : data) {
				if (menuID==menuEntity.getId()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public List<MenuInfo> getAuthSettingList(int userID) {
		return dao.getAuthSettingList(userID);
	}

	@Override
	public List<MenuInfo> getRoleAuthSettingList(int roleID) {
		return dao.getRoleAuthSettingList(roleID);
	}

	@Override
	public List<MenuInfo> getListMenuByParId(int parId) {
		return dao.getListMenuByParId(parId);
	}

	@Override
	public MenuInfo getMenuById(int id) {
		return dao.getMenuById(id);
	}

	@Override
	public boolean addMenu(MenuInfo req) {
		return dao.addMenu(req);
	}



}
