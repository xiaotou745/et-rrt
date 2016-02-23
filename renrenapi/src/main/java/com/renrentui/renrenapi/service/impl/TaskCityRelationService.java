package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IPublicProvinceCityDao;
import com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao;
import com.renrentui.renrenapi.service.inter.ITaskCityRelationService;
import com.renrentui.renrenentity.PublicProvinceCity;
@Service
public class TaskCityRelationService implements ITaskCityRelationService{
	@Autowired
	private ITaskCityRelationDao taskCityRelationDao;
	@Override
	public PublicProvinceCity getTaskCity(Long taskId) {
		return taskCityRelationDao.getTaskCity(taskId);
	}

}
