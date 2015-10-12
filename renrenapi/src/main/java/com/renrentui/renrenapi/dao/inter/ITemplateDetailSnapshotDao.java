package com.renrentui.renrenapi.dao.inter;


public interface ITemplateDetailSnapshotDao {
	public int copySnapshot(Long templateId,Long snapshotId) ;
	public int deleteBySnapshotTemplateId(Long snapshotTemplateId) ;
}