package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.TaskTag;

public interface ITaskTagService {
	List<TaskTag> getAll();
	int update(TaskTag record);
	int insert(TaskTag record);
}
