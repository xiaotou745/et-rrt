package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import com.renrentui.renrenapi.dao.inter.IMenuInfoDao;
import com.renrentui.renrenapi.service.inter.IMenuInfoService;
import com.renrentui.renrenentity.MenuInfo;
import com.renrentui.renrenentity.domain.MenuEntity;
import com.renrentui.renrenentity.req.AddNewMenuReq;

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
	/**
	 * 根据AuthCode判断用户是否有该权限
	 * 2015年12月2日14:42:12
	 * 茹化肖
	 */
	@Override
	public boolean checkHasAuthByCode(int userID,String authCode) {
		List<MenuInfo> data=dao.getMenuListByUserID(userID);
		if (data!=null&&data.size()>0) {
			for (MenuInfo menuEntity : data) {
				if (authCode.equals(menuEntity.getAuthCode())) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public List<MenuEntity> getAuthSettingList(int userID) {
		return dao.getAuthSettingList(userID);
	}

	@Override
	public List<MenuEntity> getMenuListByRoleID(int roleID) {
		return dao.getMenuListByRoleID(roleID);
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
