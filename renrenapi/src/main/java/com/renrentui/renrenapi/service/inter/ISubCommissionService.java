package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.req.StrategyModelReq;

/**
 * 分佣
 * @author ofmyi_000
 *
 */
public interface ISubCommissionService {
	/**
	 * 添加分佣策略
	 * 茹化肖
	 * 2015年12月29日16:38:26
	 * @param req
	 * @return
	 */
	int addStrategy(StrategyModelReq req);
}
