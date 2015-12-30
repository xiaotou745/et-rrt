package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.Strategy;
import com.renrentui.renrenentity.StrategyChild;

/**
 * 分佣策略表
 * @author ofmyi_000
 *
 */
public interface IStrategyDao {

	/**
	 * 插入一个策略
	 * @param model
	 * @return
	 */
	int insertStrategy(Strategy model);
	
	/**
	 * 插入策略子层级
	 * @param model
	 * @return
	 */
	int insertStrategyChild(StrategyChild model);
	/**
	 * 获取策略列表
	 * @param req
	 * @return
	 */
	List<Strategy> getStrategyList(Strategy req);
	/**
	 * 更新策略状态
	 * @param req
	 * @return
	 */
	int updateStatus(Strategy req);
	/**
	 * 
	 * 获取当前启用的策略
	 * @return
	 */
	Strategy getCruuentStrategy();
}
