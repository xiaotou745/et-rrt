package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.Template;

public interface ITemplateDao {
    int deleteByPrimaryKey(Long id);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);
}