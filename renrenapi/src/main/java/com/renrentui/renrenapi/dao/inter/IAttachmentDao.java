package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.Attachment;
import com.renrentui.renrenentity.TaskCityRelation;

public interface IAttachmentDao {
    List<Attachment> selectByTaskId(Long taskId);
    int insertList(List<Attachment> recordList);
}