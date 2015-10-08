package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.domain.CheckTask;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.req.TaskDetailReq;
import com.renrentui.renrenentity.req.TaskReq;

public interface IRenRenTaskDao {
    int deleteByPrimaryKey(Long id);

    int insert(RenRenTask record);

    int insertSelective(RenRenTask record);

    RenRenTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RenRenTask record);

    int updateByPrimaryKey(RenRenTask record);
    
    TaskDetail getTaskDetail(TaskDetailReq req);
    
    CheckTask checkTask(TaskDetailReq req);

	List<TaskModel> getNewTaskList(TaskReq req);

	int getNewTaskTotal(TaskReq req);
}