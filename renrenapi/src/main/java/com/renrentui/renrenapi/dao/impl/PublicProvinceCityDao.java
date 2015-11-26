package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IPublicProvinceCityDao;
import com.renrentui.renrenentity.PublicProvinceCity;
@Repository
public class PublicProvinceCityDao extends DaoBase implements
		IPublicProvinceCityDao {

	@Override
	public List<PublicProvinceCity> getOpenCityList(String cityName) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IPublicProvinceCityDao.getOpenCityList",
						cityName);
	}

	@Override
	public List<PublicProvinceCity> getAllOpenCity() {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IPublicProvinceCityDao.getAllOpenCity");
	}

}
