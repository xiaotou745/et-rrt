package com.renrentui.api.dao.inter;

import com.renrentui.entity.TemplateControl;

public interface ITemplateControlDao {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateControl record);

    int insertSelective(TemplateControl record);

    TemplateControl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemplateControl record);

    int updateByPrimaryKey(TemplateControl record);
}