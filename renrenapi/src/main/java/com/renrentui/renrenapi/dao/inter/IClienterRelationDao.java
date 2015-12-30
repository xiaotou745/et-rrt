package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterRelation;

public interface IClienterRelationDao {
	
	/**
	 * 通过骑士ID查询自己的上级关系
	 * @param clienterId
	 * @return
	 */
	List<ClienterRelation> getRelastionListByClienterId(Long clienterId);
	
	/***
	 * 插入一个关系
	 * @param model
	 * @return
	 */
	int insertClienterRelation(ClienterRelation model);

}
