package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.TaskDetailReq;

public interface IRenRenTaskDao {
    int deleteByPrimaryKey(Long id);

    int insert(RenRenTask record);

    int insertSelective(RenRenTask record);

    RenRenTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RenRenTask record);

    int updateByPrimaryKey(RenRenTask record);
    
    TaskDetail getTaskDetail(TaskDetailReq req);
}