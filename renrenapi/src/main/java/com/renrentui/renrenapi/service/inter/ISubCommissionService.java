package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.Strategy;
import com.renrentui.renrenentity.StrategyChild;
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
	
	/**
	 * 获取策略列表
	 * @param req
	 * @return
	 */
	List<Strategy> getStrategyList(Strategy req);
	/**
	 * 边更状态
	 * @param req
	 * @return
	 */
	int updateStatus(Strategy req);
	/**
	 * 获取当前的策略
	 * @return
	 */
	Strategy getCruuentStrategy();
	/**
	 * 根据ID获取策略信息
	 * @param id
	 * @return
	 */
	Strategy getStrategyById(Long id);
	/**
	 * 根据ID获取策略子集信息
	 * @param id
	 * @return
	 */
	List<StrategyChild> getStrategyChildById(Long id);
}
