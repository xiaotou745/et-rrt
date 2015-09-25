package com.renrentui.api.dao.inter;

import com.renrentui.entity.TemplateDetail;

public interface ITemplateDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateDetail record);

    int insertSelective(TemplateDetail record);

    TemplateDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemplateDetail record);

    int updateByPrimaryKey(TemplateDetail record);
}