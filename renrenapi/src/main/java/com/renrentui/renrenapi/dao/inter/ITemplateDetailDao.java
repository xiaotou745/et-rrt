package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.TemplateDetail;

public interface ITemplateDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateDetail record);

    int insertSelective(TemplateDetail record);

    TemplateDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemplateDetail record);

    int updateByPrimaryKey(TemplateDetail record);
}