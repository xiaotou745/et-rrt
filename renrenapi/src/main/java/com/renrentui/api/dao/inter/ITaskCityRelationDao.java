package com.renrentui.api.dao.inter;

import com.renrentui.entity.TaskCityRelation;

public interface ITaskCityRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(TaskCityRelation record);

    int insertSelective(TaskCityRelation record);

    TaskCityRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskCityRelation record);

    int updateByPrimaryKey(TaskCityRelation record);
}