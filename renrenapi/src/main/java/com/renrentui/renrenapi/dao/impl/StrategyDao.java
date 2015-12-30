package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IStrategyDao;
import com.renrentui.renrenentity.Strategy;
import com.renrentui.renrenentity.StrategyChild;
/**
 * 分佣
 * @author ofmyi_000
 *
 */
@Repository
public class StrategyDao extends DaoBase implements IStrategyDao{
	
	/**
	 * 插入一个策略
	 */
	@Override
	public int insertStrategy(Strategy model) {
		int res= getMasterSqlSessionUtil().insert("IStrategyDao.insertStrategy", model);
		return res;
	}
	/**
	 * 插入一个策略子层级
	 */
	@Override
	public int insertStrategyChild(StrategyChild model) {
		return getMasterSqlSessionUtil().insert("IStrategyDao.insertStrategyChild", model);
	}
	/**
	 * 获取策略列表
	 */
	@Override
	public List<Strategy> getStrategyList(Strategy req) {
		return getReadOnlySqlSessionUtil().selectList("IStrategyDao.getStrategyList", req);
	}
	/**
	 * 更新策略状态
	 */
	@Override
	public int updateStatus(Strategy req) {
		return getMasterSqlSessionUtil().insert("IStrategyDao.updateStatus", req);
	}
	/**
	 * 获取当前启用的策略
	 */
	@Override
	public Strategy getCruuentStrategy() {
		return getReadOnlySqlSessionUtil().selectOne("IStrategyDao.getCruuentStrategy");
	}

}
