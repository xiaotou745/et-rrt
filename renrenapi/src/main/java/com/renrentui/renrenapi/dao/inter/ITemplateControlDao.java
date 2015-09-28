package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.TemplateControl;

public interface ITemplateControlDao {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateControl record);

    int insertSelective(TemplateControl record);

    TemplateControl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemplateControl record);

    int updateByPrimaryKey(TemplateControl record);
}