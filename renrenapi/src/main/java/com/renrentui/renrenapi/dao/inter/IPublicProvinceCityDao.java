package com.renrentui.renrenapi.dao.inter;
import java.util.List;

import com.renrentui.renrenentity.PublicProvinceCity;

public interface IPublicProvinceCityDao {    
	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 */
    List<PublicProvinceCity> getOpenCityList(String  cityName);

   
	/**
	 * 获取开通城市的省市区 
	 * @author CaoHeYang
	 * @Date 20150727
	 * @return 
	 */
	
    List<PublicProvinceCity> getAllOpenCity();

}