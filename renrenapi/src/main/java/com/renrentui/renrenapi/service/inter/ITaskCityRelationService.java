package com.renrentui.renrenapi.service.inter;

import java.util.List;
import java.util.Map;

import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.TaskCityRelation;
import com.renrentui.renrenentity.domain.TaskRegion;

public interface ITaskCityRelationService {
	List<TaskCityRelation> getTaskCityRelationList(Long taskId);
	Map<Long,List<TaskRegion>> getTaskCityRelationDetailList(List<Long> taskIds);
}
