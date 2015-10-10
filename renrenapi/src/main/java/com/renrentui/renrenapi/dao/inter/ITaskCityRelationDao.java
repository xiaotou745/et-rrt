package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.TaskCityRelation;

public interface ITaskCityRelationDao {
    int insertList(List<TaskCityRelation> recordList);
    List<TaskCityRelation> selectByTaskId(Long taskId);
}