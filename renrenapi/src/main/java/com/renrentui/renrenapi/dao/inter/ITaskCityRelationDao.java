package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.TaskCityRelation;

public interface ITaskCityRelationDao {
    int insertList(List<TaskCityRelation> recordList);
    List<TaskCityRelation> selectByTaskId(Long taskId);
    List<TaskCityRelation> selectByTaskIds(List<Long> taskIds);
    int deleteByTaskId(Long taskId);
}