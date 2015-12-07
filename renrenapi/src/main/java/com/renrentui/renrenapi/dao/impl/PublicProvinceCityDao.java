package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IPublicProvinceCityDao;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.domain.OpenCityModel;
import com.renrentui.renrenentity.req.HotAndPublicCityReq;
@Repository
public class PublicProvinceCityDao extends DaoBase implements
		IPublicProvinceCityDao {

	@Override
	public List<OpenCityModel> getOpenCityList(HotAndPublicCityReq hotAndPublicCityReq) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IPublicProvinceCityDao.getOpenCityList",
						hotAndPublicCityReq);
	}

	@Override
	public List<PublicProvinceCity> getAllOpenCity() {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IPublicProvinceCityDao.getAllOpenCity");
	}

}
