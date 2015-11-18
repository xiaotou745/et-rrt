package com.renrentui.renrenapi.dao.inter;

import java.util.ArrayList;
import java.util.List;

import com.renrentui.renrenentity.domain.ControlInfo;
import com.renrentui.renrenentity.domain.TemplateModel;
import com.renrentui.renrenentity.req.TemplateSnapshotReq;
import com.renrentui.renrenentity.TemplateDetail;

public interface ITemplateDetailDao {
    int deleteByTemplateId(Long templateId);
    /**
     * 插入一个模板详情
     * 茹化肖
     * 2015年11月16日16:09:02
     * @param record
     * @return
     */
    int insert(TemplateDetail record);
    int insertList(List<TemplateDetail> recordList);
    /**
     * 获取合同详细控件信息
     * @param emplateId
     * @return
     */
    ArrayList<ControlInfo> getTemplateList(Long templateId,Long orderId);
	public List<TemplateDetail> listByTemplateId(Long templateId) ;

}