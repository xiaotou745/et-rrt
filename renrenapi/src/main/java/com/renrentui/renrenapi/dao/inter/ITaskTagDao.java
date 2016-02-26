package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.TaskTag;

public interface ITaskTagDao {
	List<TaskTag> getAll();
	int update(TaskTag record);
	int insert(TaskTag record);
}
