package com.renrentui.renrenentity;

import java.util.Date;

public class Strategy {
	/**

	 * ID

	 */

	private Long id;



	/**

	 * 策略名称

	 */

	private String strategyName;



	/**

	 * 总分佣层数(向上)

	 */

	private Integer levalCount;



	/**

	 * 最大累计分佣比例

	 */

	private Double maxPercentage;



	/**

	 * 当前分佣比例

	 */

	private Double percentage;



	/**

	 * 操作人

	 */

	private String optName;



	/**

	 * 操作时间

	 */

	private Date optTime;



	/**

	 * 备注信息

	 */

	private String remark;



	/**

	 * 创建时间

	 */

	private Date createDate;


	private Integer status;

	//1 禁用 2 启用 3删除
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**

	 * 获取ID

	 */

	public Long getId() {

		return id;

	}

	/**

	 * 设置ID

	 * @param id ID

	 */

	public void setId(Long id) {

		this.id = id;

	}



	/**

	 * 获取策略名称

	 */

	public String getStrategyName() {

		return strategyName;

	}

	/**

	 * 设置策略名称

	 * @param strategyName 策略名称

	 */

	public void setStrategyName(String strategyName) {

		this.strategyName = strategyName;

	}



	/**

	 * 获取总分佣层数(向上)

	 */

	public Integer getLevalCount() {

		return levalCount;

	}

	/**

	 * 设置总分佣层数(向上)

	 * @param levalCount 总分佣层数(向上)

	 */

	public void setLevalCount(Integer levalCount) {

		this.levalCount = levalCount;

	}



	/**

	 * 获取最大累计分佣比例

	 */

	public Double getMaxPercentage() {

		return maxPercentage;

	}

	/**

	 * 设置最大累计分佣比例

	 * @param maxPercentage 最大累计分佣比例

	 */

	public void setMaxPercentage(Double maxPercentage) {

		this.maxPercentage = maxPercentage;

	}



	/**

	 * 获取当前分佣比例

	 */

	public Double getPercentage() {

		return percentage;

	}

	/**

	 * 设置当前分佣比例

	 * @param percentage 当前分佣比例

	 */

	public void setPercentage(Double percentage) {

		this.percentage = percentage;

	}



	/**

	 * 获取操作人

	 */

	public String getOptName() {

		return optName;

	}

	/**

	 * 设置操作人

	 * @param optName 操作人

	 */

	public void setOptName(String optName) {

		this.optName = optName;

	}



	/**

	 * 获取操作时间

	 */

	public Date getOptTime() {

		return optTime;

	}

	/**

	 * 设置操作时间

	 * @param optTime 操作时间

	 */

	public void setOptTime(Date optTime) {

		this.optTime = optTime;

	}



	/**

	 * 获取备注信息

	 */

	public String getRemark() {

		return remark;

	}

	/**

	 * 设置备注信息

	 * @param remark 备注信息

	 */

	public void setRemark(String remark) {

		this.remark = remark;

	}



	/**

	 * 获取创建时间

	 */

	public Date getCreateDate() {

		return createDate;

	}

	/**

	 * 设置创建时间

	 * @param createDate 创建时间

	 */

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;

	}



}
