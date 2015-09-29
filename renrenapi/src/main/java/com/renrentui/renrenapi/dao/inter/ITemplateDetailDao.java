package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.TemplateDetail;

public interface ITemplateDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateDetail record);
    int insertList(List<TemplateDetail> recordList);
}