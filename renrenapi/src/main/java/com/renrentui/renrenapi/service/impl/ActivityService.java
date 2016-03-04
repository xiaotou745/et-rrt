package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IActivityDao;
import com.renrentui.renrenapi.service.inter.IActivityService;
import com.renrentui.renrenentity.Activity; 
import com.renrentui.renrenentity.req.UpdateActivityReq;

@Service
public class ActivityService implements  IActivityService{

	@Autowired
	private IActivityDao iActivityDao;
	@Override
	public List<Activity> getList() {
		return iActivityDao.getList();
	}
	@Override
	public int shutUpActivity(UpdateActivityReq req) {
		return iActivityDao.shutUpActivity(req);
	}
	@Override
	public int startUpActivity(UpdateActivityReq req) {
		return iActivityDao.startUpActivity(req);
	}
	@Override
	public Activity getSingleActivity(int id) {
		return iActivityDao.getSingleActivity(id);
	}
	@Override
	public int updateActityData(int id) {
		return iActivityDao.updateActityData(id);
	}

}
