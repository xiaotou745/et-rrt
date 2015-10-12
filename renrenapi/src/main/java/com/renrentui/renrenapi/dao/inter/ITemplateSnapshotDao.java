package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.TemplateSnapshot;
import com.renrentui.renrenentity.req.TemplateSnapshotReq;

public interface ITemplateSnapshotDao {
	public int copySnapshot(TemplateSnapshotReq req) ;
	public TemplateSnapshot detailById(Long id) ;
	public TemplateSnapshot detailByTemplateId(Long templateId) ;
	public int deleteByTemplateId(Long templateId) ;
}