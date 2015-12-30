package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IStrategyDao;
import com.renrentui.renrenapi.service.inter.ISubCommissionService;
import com.renrentui.renrenentity.Strategy;
import com.renrentui.renrenentity.StrategyChild;
import com.renrentui.renrenentity.req.StrategyModelReq;
/**
 * 分佣
 * @author ofmyi_000
 *
 */
@Service
public class SubCommissionService implements ISubCommissionService{
	@Autowired
	private IStrategyDao strategyDao;
	/**
	 * 添加分佣策略
	 * 茹化肖
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int addStrategy(StrategyModelReq req) {
		//0.校验
		Double maxDouble=50.0;//系统设置的最大比例
		if(req.getPercentage()>maxDouble)
			return -2;//当前设置的分佣总比例大于系统给的比例
		if(req.getLevalCount()!=req.getChildList().size())
			return -3;//层级不一致
		//1.插入Strategy
		Strategy star=new Strategy();
		star.setStrategyName(req.getStrategyName());
		star.setPercentage(req.getPercentage());
		star.setLevalCount(req.getLevalCount());
		star.setOptName(req.getOptName());
		star.setRemark(req.getRemark());
		star.setMaxPercentage(maxDouble);
		int res=strategyDao.insertStrategy(star);
		//2.插入层级
		for (int i = 0; i < req.getChildList().size(); i++) {
			StrategyChild child=req.getChildList().get(i);
			child.setStrategyId(star.getId());
			strategyDao.insertStrategyChild(child);
		}
		return 1;
	}
	/**
	 * 获取策略列表
	 */
	@Override
	public List<Strategy> getStrategyList(Strategy req) {
		return strategyDao.getStrategyList(req);
	}
	/**
	 * 更新策略状态
	 */
	@Override
	public int updateStatus(Strategy req) {
		return strategyDao.updateStatus(req);
	}
	/**
	 * 
	 * 获取当前的策略
	 */
	@Override
	public Strategy getCruuentStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

}
