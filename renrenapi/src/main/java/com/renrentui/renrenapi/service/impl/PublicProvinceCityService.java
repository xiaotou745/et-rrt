package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IPublicProvinceCityDao;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrenentity.PublicProvinceCity;



@Service
public class PublicProvinceCityService implements IPublicProvinceCityService {
	@Autowired
	private IPublicProvinceCityDao publicProvinceCityDao;
	@Autowired
	private RedisService redisService;

	/**
	 * 获取开放城市列表（非分页）
	 * 
	 * @author CaoHeYang
	 */
	@Override
	public List<PublicProvinceCity> getOpenCityList(String cityName) {
		return publicProvinceCityDao.getOpenCityList(cityName);
	};

	

	

	@Override
	public List<PublicProvinceCity> getOpenCityListFromRedis() {
		List<PublicProvinceCity> listdata=redisService.get(RedissCacheKey.RR_PublicProvinceCity, List.class);
		if (listdata==null||listdata.size()==0) {
			listdata=publicProvinceCityDao.getAllOpenCity();
			if (listdata!=null||listdata.size()>0) {
				redisService.set(RedissCacheKey.RR_PublicProvinceCity, listdata);
			}
		}
		return listdata;
	}
	@Override
	public List<PublicProvinceCity> getOpenCityByJiBie(int jiBie)
	{
		List<PublicProvinceCity> list = getOpenCityListFromRedis();
		List<PublicProvinceCity> listnew = new ArrayList<PublicProvinceCity>();

		for (PublicProvinceCity item : list) {
			if (item.getJiBie() == jiBie) {
				listnew.add(item);
			}
		}
		return listnew;
	}
	
	/**
	 * 根据城市Id获取对应的区县列表
	 * @author zhaohailong
	 */
	@Override
	public List<PublicProvinceCity> getOpenCityDistrict(int cityId) {
		List<PublicProvinceCity> list = getOpenCityListFromRedis();
		List<PublicProvinceCity> listnew = new ArrayList<PublicProvinceCity>();

		for (PublicProvinceCity item : list) {
			if (item.getJiBie() == 3&&item.getCode()==cityId) {
				listnew.add(item);
			}
		}
		return listnew;
	}





	@Override
	public Map<Integer, String> getOpenCityMap() {
		List<PublicProvinceCity> list = getOpenCityListFromRedis();
		Map<Integer, String> listnew = new HashMap<>();

		for (PublicProvinceCity item : list) {
			if (!listnew.containsKey(item.getCode())) {
				listnew.put(item.getCode(), item.getName());
			}
		}
		return listnew;
	}
}
