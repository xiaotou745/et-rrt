package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.ITaskTagDao;
import com.renrentui.renrenapi.service.inter.ITaskTagService;
import com.renrentui.renrenentity.TaskTag;

@Service
public class TaskTagService implements ITaskTagService{
@Autowired
private ITaskTagDao taskTagDao;
	@Override
	public List<TaskTag> getAll() {
return taskTagDao.getAll();
	}

	@Override
	public int update(TaskTag record) {
return taskTagDao.update(record);
	}

	@Override
	public int insert(TaskTag record) {
return taskTagDao.insert(record);
	}

}
