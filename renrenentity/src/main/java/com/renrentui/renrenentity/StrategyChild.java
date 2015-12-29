package com.renrentui.renrenentity;

public class StrategyChild {
	/**

	 * ID

	 */

	private Long id;



	/**

	 * 策略ID

	 */

	private Long strategyId;



	/**

	 * 层级编号

	 */

	private Integer levalNo;



	/**

	 * 该层级分佣百分比

	 */

	private Double percentage;





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

	 * 获取策略ID

	 */

	public Long getStrategyId() {

		return strategyId;

	}

	/**

	 * 设置策略ID

	 * @param strategyId 策略ID

	 */

	public void setStrategyId(Long strategyId) {

		this.strategyId = strategyId;

	}



	/**

	 * 获取层级编号

	 */

	public Integer getLevalNo() {

		return levalNo;

	}

	/**

	 * 设置层级编号

	 * @param levalNo 层级编号

	 */

	public void setLevalNo(Integer levalNo) {

		this.levalNo = levalNo;

	}



	/**

	 * 获取该层级分佣百分比

	 */

	public Double getPercentage() {

		return percentage;

	}

	/**

	 * 设置该层级分佣百分比

	 * @param percentage 该层级分佣百分比

	 */

	public void setPercentage(Double percentage) {

		this.percentage = percentage;

	}

}
