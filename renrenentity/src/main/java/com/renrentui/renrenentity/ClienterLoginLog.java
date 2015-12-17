package com.renrentui.renrenentity;

import java.util.Date;



/**

 * 实体类ClienterLoginLog. (属性说明自动提取数据库字段的描述信息)

 * @author wangyuchuan

 * @date 2015-12-16 19:09:27

 *

 */

public class ClienterLoginLog {

	
	private Long id;


	private Integer clienterId;


	private String phoneNo;



	private String sSID;



	private String operSystem;



	private String operSystemModel;



	private String phoneType;


	private String appVersion;


	private Date createTime;



	private String description;

	private Integer isSuccess;





	/**

	 * 获取

	 */

	public Long getId() {

		return id;

	}

	/**

	 * 设置

	 * @param id 

	 */

	public void setId(Long id) {

		this.id = id;

	}



	/**

	 * 获取

	 */

	public Integer getClienterId() {

		return clienterId;

	}

	/**

	 * 设置

	 * @param clienterId 

	 */

	public void setClienterId(Integer clienterId) {

		this.clienterId = clienterId;

	}



	/**

	 * 获取电话

	 */

	public String getPhoneNo() {

		return phoneNo;

	}

	/**

	 * 设置电话

	 * @param phoneNo 电话

	 */

	public void setPhoneNo(String phoneNo) {

		this.phoneNo = phoneNo;

	}



	/**

	 * 获取

	 */

	public String getSSID() {

		return sSID;

	}

	/**

	 * 设置

	 * @param sSID 

	 */

	public void setSSID(String sSID) {

		this.sSID = sSID;

	}



	/**

	 * 获取手机操作系统android,ios

	 */

	public String getOperSystem() {

		return operSystem;

	}

	/**

	 * 设置手机操作系统android,ios

	 * @param operSystem 手机操作系统android,ios

	 */

	public void setOperSystem(String operSystem) {

		this.operSystem = operSystem;

	}



	/**

	 * 获取手机具体型号5.0

	 */

	public String getOperSystemModel() {

		return operSystemModel;

	}

	/**

	 * 设置手机具体型号5.0

	 * @param operSystemModel 手机具体型号5.0

	 */

	public void setOperSystemModel(String operSystemModel) {

		this.operSystemModel = operSystemModel;

	}



	/**

	 * 获取手机类型,三星、苹果

	 */

	public String getPhoneType() {

		return phoneType;

	}

	/**

	 * 设置手机类型,三星、苹果

	 * @param phoneType 手机类型,三星、苹果

	 */

	public void setPhoneType(String phoneType) {

		this.phoneType = phoneType;

	}



	/**

	 * 获取

	 */

	public String getAppVersion() {

		return appVersion;

	}

	/**

	 * 设置

	 * @param appVersion 

	 */

	public void setAppVersion(String appVersion) {

		this.appVersion = appVersion;

	}



	/**

	 * 获取创建时间

	 */

	public Date getCreateTime() {

		return createTime;

	}

	/**

	 * 设置创建时间

	 * @param createTime 创建时间

	 */

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;

	}



	/**

	 * 获取描述信息

	 */

	public String getDescription() {

		return description;

	}

	/**

	 * 设置描述信息

	 * @param description 描述信息

	 */

	public void setDescription(String description) {

		this.description = description;

	}



	/**

	 * 获取是否成功   1 成功   0失败

	 */

	public Integer getIsSuccess() {

		return isSuccess;

	}

	/**

	 * 设置是否成功   1 成功   0失败

	 * @param isSuccess 是否成功   1 成功   0失败

	 */

	public void setIsSuccess(Integer isSuccess) {

		this.isSuccess = isSuccess;

	}

}
