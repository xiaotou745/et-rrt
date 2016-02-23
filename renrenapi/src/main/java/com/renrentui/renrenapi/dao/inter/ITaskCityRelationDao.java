package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.TaskCityRelation;

public interface ITaskCityRelationDao {
    int insertList(List<TaskCityRelation> recordList);
    List<TaskCityRelation> selectByTaskId(Long taskId);
    int deleteByTaskId(Long taskId);
	/**
	 * 获取任务的投放区域  任务详情页展示用
	 * 茹化肖
	 * 2015年12月23日11:25:37
	 * @param taskId
	 * @return
	 */
	PublicProvinceCity getTaskCity(Long taskId);
}