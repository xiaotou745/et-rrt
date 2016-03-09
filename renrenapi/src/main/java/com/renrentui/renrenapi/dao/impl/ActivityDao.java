package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IActivityDao;
import com.renrentui.renrenentity.Activity;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ActivityReq;
import com.renrentui.renrenentity.req.PagedActivityReq;
import com.renrentui.renrenentity.req.UpdateActivityReq;

@Repository
public class ActivityDao extends DaoBase implements IActivityDao {

	@Override
	public List<Activity> getList() {
		return getReadOnlySqlSessionUtil().selectList("IActivityDao.getList");
	}

	@Override
	public int shutUpActivity(UpdateActivityReq req) {
		return getMasterSqlSessionUtil().update("IActivityDao.shutUpActivity",req);
	}

	@Override
	public int startUpActivity(UpdateActivityReq req) {
		return getMasterSqlSessionUtil().update("IActivityDao.startUpActivity",req);
	}

	@Override
	public Activity getSingleActivity(ActivityReq req) {
		return getReadOnlySqlSessionUtil().selectOne("IActivityDao.getSingleActivity",req);
	}

	@Override
	public int updateActityData(int id) {
		return getMasterSqlSessionUtil().update("IActivityDao.updateActityData",id);
	}

}
