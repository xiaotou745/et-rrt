package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.req.CRelationReq;

/**
 * 层级关系
 * @author ofmyi_000
 *
 */
public interface IClienterRelationService {
	/**
	 * 通过推荐人手机号统计下面的信息
	 * @param req
	 * @return
	 */
	List<ClienterRelationModel> getClienterRelationModelsByPhone(CRelationReq req);

}
