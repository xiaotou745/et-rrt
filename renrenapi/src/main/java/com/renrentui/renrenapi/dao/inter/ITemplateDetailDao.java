package com.renrentui.renrenapi.dao.inter;

import java.util.ArrayList;
import java.util.List;
import com.renrentui.renrenentity.domain.ControlInfo;
import com.renrentui.renrenentity.TemplateDetail;

public interface ITemplateDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateDetail record);
    int insertList(List<TemplateDetail> recordList);
    /**
     * 获取合同详细控件信息
     * @param emplateId
     * @return
     */
    ArrayList<ControlInfo> getTemplateList(Long emplateId);
}