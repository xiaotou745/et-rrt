package com.renrentui.renrenentity;

import java.util.Date;

public class ClienterRelation {
	/**

	 * ID

	 */

	private Long id;



	/**

	 * 地推员ID(Self)

	 */

	private Long clienterId;



	/**

	 * 上级地推员ID

	 */

	private Long otherClienterId;



	/**

	 * OtheClienterId 相对Clienter的级别(向上级别)

	 */

	private Integer ocJibie;



	/**

	 * 当前ClienterID相对于根的层级

	 */

	private Integer clienterLevel;



	/**

	 * 创建时间

	 */

	private Date createDate;





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

	 * 获取地推员ID(Self)

	 */

	public Long getClienterId() {

		return clienterId;

	}

	/**

	 * 设置地推员ID(Self)

	 * @param clienterId 地推员ID(Self)

	 */

	public void setClienterId(Long clienterId) {

		this.clienterId = clienterId;

	}



	/**

	 * 获取上级地推员ID

	 */

	public Long getOtherClienterId() {

		return otherClienterId;

	}

	/**

	 * 设置上级地推员ID

	 * @param otherClienterId 上级地推员ID

	 */

	public void setOtherClienterId(Long otherClienterId) {

		this.otherClienterId = otherClienterId;

	}



	/**

	 * 获取OtheClienterId 相对Clienter的级别(向上级别)

	 */

	public Integer getOcJibie() {

		return ocJibie;

	}

	/**

	 * 设置OtheClienterId 相对Clienter的级别(向上级别)

	 * @param ocJibie OtheClienterId 相对Clienter的级别(向上级别)

	 */

	public void setOcJibie(Integer ocJibie) {

		this.ocJibie = ocJibie;

	}



	/**

	 * 获取当前ClienterID相对于根的层级

	 */

	public Integer getClienterLevel() {

		return clienterLevel;

	}

	/**

	 * 设置当前ClienterID相对于根的层级

	 * @param clienterLevel 当前ClienterID相对于根的层级

	 */

	public void setClienterLevel(Integer clienterLevel) {

		this.clienterLevel = clienterLevel;

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
