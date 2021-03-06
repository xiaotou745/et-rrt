package com.renrentui.renrenapi.service.inter;

import java.util.List;
import java.util.Map;

import com.renrentui.renrenentity.PublicProvinceCity;





/**
 * 省市区通用  业务逻辑层  
 * @author CaoHeYang
 * 20150720
 */
public interface IPublicProvinceCityService {

	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 * @param cityName 城市名称
	 */
	 List<PublicProvinceCity> getOpenCityList(String  cityName);
	 
	/**
	 * 从Redis获取开放城市列表（非分页）
	 * @author zhaohailong
	 * @param cityName 城市名称
	 */
	 List<PublicProvinceCity> getOpenCityListFromRedis();
	 
	 Map<Integer,String> getOpenCityMap();
	 /**
	  * 获取开通市
	  * 赵海龙
	  * 2015年7月29日 10:40:36
	  * */
	 List<PublicProvinceCity> getOpenCityByJiBie(int jiBie);
	 
	 
	/**
	 * 根据城市Id获取对应的区县列表
	 * @author zhaohailong
	 */
	 List<PublicProvinceCity> getOpenCityDistrict(int cityId);

}
