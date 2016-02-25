package com.renrentui.renrenentity;

import java.util.Date;

public class Activity {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 活动名称
	 */
	private String activityName;

	/**
	 * 奖励金额
	 */
	private Double rewardMoney;

	/**
	 * 发放概率，整数
	 */
	private Integer grantProbability;

	/**
	 * 已发放数量
	 */
	private Integer hadRewardCount;

	/**
	 * 已发放总金额
	 */
	private Double hadRewardMoney;

	/**
	 * 
	 */
	private Date startTime;

	/**
	 * 
	 */
	private Date endTime;

	/**
	 * 状态默认1进行中，0关闭
	 */
	private Integer status;


	/**
	 * 获取主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置主键
	 * @param id 主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取活动名称
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * 设置活动名称
	 * @param activityName 活动名称
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * 获取奖励金额
	 */
	public Double getRewardMoney() {
		return rewardMoney;
	}
	/**
	 * 设置奖励金额
	 * @param rewardMoney 奖励金额
	 */
	public void setRewardMoney(Double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}

	/**
	 * 获取发放概率，整数
	 */
	public Integer getGrantProbability() {
		return grantProbability;
	}
	/**
	 * 设置发放概率，整数
	 * @param grantProbability 发放概率，整数
	 */
	public void setGrantProbability(Integer grantProbability) {
		this.grantProbability = grantProbability;
	}

	/**
	 * 获取已发放数量
	 */
	public Integer getHadRewardCount() {
		return hadRewardCount;
	}
	/**
	 * 设置已发放数量
	 * @param hadRewardCount 已发放数量
	 */
	public void setHadRewardCount(Integer hadRewardCount) {
		this.hadRewardCount = hadRewardCount;
	}

	/**
	 * 获取已发放总金额
	 */
	public Double getHadRewardMoney() {
		return hadRewardMoney;
	}
	/**
	 * 设置已发放总金额
	 * @param hadRewardMoney 已发放总金额
	 */
	public void setHadRewardMoney(Double hadRewardMoney) {
		this.hadRewardMoney = hadRewardMoney;
	}

	/**
	 * 获取
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置
	 * @param startTime 
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置
	 * @param endTime 
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取状态默认1进行中，0关闭
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置状态默认1进行中，0关闭
	 * @param status 状态默认1进行中，0关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
