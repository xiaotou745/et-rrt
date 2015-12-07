package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IPublicProvinceCityDao;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.domain.OpenCityModel;
import com.renrentui.renrenentity.req.HotAndPublicCityReq;
import com.renrentui.renrenentity.req.ModifyCityReq;
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

	@Override
	public int modifyCity(ModifyCityReq modifyCityReq) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("openPublicCityCodeList", modifyCityReq.getOpenPublicCityCodeList());
		paramMap.put("closePublicCityCodeList", modifyCityReq.getClosePublicCityCodeList());
		paramMap.put("openHotCityCodeList", modifyCityReq.getOpenHotCityCodeList());
		paramMap.put("closeHotCityCodeList", modifyCityReq.getCloseHotCityCodeList());
		return getMasterSqlSessionUtil().update("IPublicProvinceCityDao.modifyCity", paramMap);
	}

}
