package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.TaskCityRelation;

public interface ITaskCityRelationDao {
    int deleteByPrimaryKey(Long id);

    int insertList(List<TaskCityRelation> recordList);

    int insertSelective(TaskCityRelation record);

    TaskCityRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskCityRelation record);

    int updateByPrimaryKey(TaskCityRelation record);
}