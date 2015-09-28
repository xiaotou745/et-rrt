package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.Attachment;

public interface IAttachmentDao {
    int deleteByPrimaryKey(Long id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);
}