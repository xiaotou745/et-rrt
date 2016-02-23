package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IPublicProvinceCityDao;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.domain.OpenCityModel;
import com.renrentui.renrenentity.req.HotAndPublicCityReq;
import com.renrentui.renrenentity.req.ModifyCityReq;



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
	public List<OpenCityModel> getOpenCityList(HotAndPublicCityReq hotAndPublicCityReq) {
		return publicProvinceCityDao.getOpenCityList(hotAndPublicCityReq);
	};

	

	
	/**
	 * 获取所有开发省份 城市 区域
	 * 
	 */
	@Override
	public List<PublicProvinceCity> getOpenCityListFromRedis() {
		List<PublicProvinceCity> listdata=redisService.get(RedissCacheKey.RR_PublicProvinceCity, List.class); 
		if (listdata==null||listdata.size()==0) {
			listdata=publicProvinceCityDao.getAllOpenCity();
			if (listdata!=null||listdata.size()>0) {
				redisService.set(RedissCacheKey.RR_PublicProvinceCity, listdata,360,TimeUnit.DAYS);
			}
		}
		return listdata;
	}
	/**
	 * 根据级别获取开发城市数据
	 */
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




	@Override
	public int modifyCity(ModifyCityReq modifyCityReq) {
		int result= publicProvinceCityDao.modifyCity(modifyCityReq);
		if (result>0) {
			redisService.remove(RedissCacheKey.RR_PublicProvinceCity);
			redisService.remove(RedissCacheKey.RR_PublicProvinceCity_Hot);
			redisService.remove(RedissCacheKey.RR_PublicProvinceCity_Version);
		}

		return result;
	}




}
