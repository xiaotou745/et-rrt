package com.renrentui.renrenapi.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IPublicProvinceCityDao;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.domain.ModifyCityDomain;
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
		 
		 ModifyCityDomain modifyCityDomain = new ModifyCityDomain();
		 if(modifyCityReq.getOpenPublicCityCodeList().length()>0){
			 modifyCityDomain.setOpenPublicCityCodeList(Arrays.asList(modifyCityReq.getOpenPublicCityCodeList().split(",")));
		 }
		 if(modifyCityReq.getClosePublicCityCodeList().length()>0){
			 modifyCityDomain.setClosePublicCityCodeList(Arrays.asList(modifyCityReq.getClosePublicCityCodeList().split(",")));
		 }
		 if(modifyCityReq.getOpenHotCityCodeList().length()>0){
			 modifyCityDomain.setOpenHotCityCodeList(Arrays.asList(modifyCityReq.getOpenHotCityCodeList().split(",")));
		 }
		 if(modifyCityReq.getCloseHotCityCodeList().length()>0){
			 modifyCityDomain.setCloseHotCityCodeList(Arrays.asList(modifyCityReq.getCloseHotCityCodeList().split(",")));
		 }
		return getMasterSqlSessionUtil().update("IPublicProvinceCityDao.modifyCity", modifyCityDomain);
	}
	/**
	 * 获取任务投放区域
	 */
	@Override
	public PublicProvinceCity getTaskCity(Long taskId) {
		return getReadOnlySqlSessionUtil().selectOne("IPublicProvinceCityDao.getTaskCity", taskId);
	}

}
