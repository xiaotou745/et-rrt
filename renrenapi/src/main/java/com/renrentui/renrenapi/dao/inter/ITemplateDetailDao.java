package com.renrentui.renrenapi.dao.inter;

import java.util.ArrayList;

import com.renrentui.renrenentity.TemplateDetail;
import com.renrentui.renrenentity.domain.ControlInfo;

public interface ITemplateDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateDetail record);

    int insertSelective(TemplateDetail record);

    TemplateDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemplateDetail record);

    int updateByPrimaryKey(TemplateDetail record);
    /**
     * 获取合同详细控件信息
     * @param emplateId
     * @return
     */
    ArrayList<ControlInfo> getTemplateList(Long emplateId);
}