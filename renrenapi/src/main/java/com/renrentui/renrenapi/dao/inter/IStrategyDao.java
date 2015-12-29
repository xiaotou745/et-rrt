package com.renrentui.renrenapi.dao.inter;

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
}
