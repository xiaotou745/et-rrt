package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.ITaskCityRelationDao;
import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrenapi.service.inter.ITaskCityRelationService;
import com.renrentui.renrencore.enums.AreaLevel;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.TaskCityRelation;
import com.renrentui.renrenentity.domain.TaskRegion;
@Service
public class TaskCityRelationService implements ITaskCityRelationService{
	@Autowired
	private ITaskCityRelationDao taskCityRelationDao;
	@Autowired
	private IPublicProvinceCityService publicProvinceCityService;
	
	@Override
	public List<TaskCityRelation> getTaskCityRelationList(Long taskId) {
		return taskCityRelationDao.selectByTaskId(taskId);
	}
	@Override
	public Map<Long, List<TaskRegion>> getTaskCityRelationDetailList(
			List<Long> taskIds) {
		Map<Long, List<TaskRegion>> result=new HashMap<Long, List<TaskRegion>>();
		Map<Integer,PublicProvinceCity> cityMap=publicProvinceCityService.getOpenCityDetailMap();
		List<TaskCityRelation> relations=taskCityRelationDao.selectByTaskIds(taskIds);
		Map<Long, List<TaskCityRelation>> group =relations.stream().collect(Collectors.groupingBy(TaskCityRelation::getTaskId));
		for (Long taskId : group.keySet()) {
			List<TaskCityRelation> taskRelations=group.get(taskId);
			List<TaskRegion> taskRegions=new ArrayList<TaskRegion>();
			//如果CityCode=-1，则表示全国
			if(taskRelations.size()==1&&taskRelations.get(0).getCityCode().intValue()==-1){
				TaskRegion taskRegion=new TaskRegion();
				taskRegion.setTaskId(taskId);
				taskRegion.setCityCode(taskRelations.get(0).getCityCode());
				taskRegion.setCityName(taskRelations.get(0).getCityName());
				taskRegions.add(taskRegion);
			}else{
				for (TaskCityRelation taskCityRelation : taskRelations) {
					PublicProvinceCity cityDetail=cityMap.get(taskCityRelation.getCityCode());
					TaskRegion taskRegion=new TaskRegion();
					taskRegion.setTaskId(taskId);
					taskRegion.setCityCode(taskCityRelation.getCityCode());
					taskRegion.setCityName(cityDetail.getName());
					if (cityDetail.getJiBie().intValue()==AreaLevel.City.value()) {
						PublicProvinceCity provinceDetail=cityMap.get(cityDetail.getParentCode());
						taskRegion.setParentCode(cityDetail.getCode());
						taskRegion.setParentName(provinceDetail.getName());
					}
					taskRegions.add(taskRegion);
				}
			}
			result.put(taskId, taskRegions);
		}
		return result;
	}

}
