package com.renrentui.renrenentity;

import java.util.Date;

/**
 * 实体类AppVersion. (属性说明自动提取数据库字段的描述信息)
 * 
 * @author edaisong.system
 * @date 2015-10-13 10:30:52
 *
 */
public class AppVersion {
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 发布状态 0待发布 1 已发布 2 取消发布
	 */
	private Integer pubStatus;
	/**
	 * 当前版本号
	 */
	private String version;
	/**
	 * 是否强制升级 1 是 0否 默认0
	 */
	private Integer isMust;
	/**
	 * 下载地址
	 */
	private String updateUrl;
	/**
	 * 升级信息 可以不填
	 */
	private String message;
	/**
	 * 客户端类型 1:Android 2 :IOS 默认Android
	 */
	private Integer platForm;
	/**
	 * 用户版本 1 骑士 2 商家 默认1骑士
	 */
	private Integer userType;
	/**
	 * 
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 是否定时 0否 1 是
	 */
	private Integer isTiming;
	/**
	 * 定时发布时间
	 */
	private Date timingDate;
	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}



	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}



	public Integer getIsMust() {
		return isMust;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}



	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public Integer getPlatForm() {
		return platForm;
	}

	public void setPlatForm(Integer platForm) {
		this.platForm = platForm;
	}



	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}



	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}



	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}



	public Integer getIsTiming() {
		return isTiming;
	}

	public void setIsTiming(Integer isTiming) {
		this.isTiming = isTiming;
	}



	public Date getTimingDate() {
		return timingDate;
	}

	public void setTimingDate(Date timingDate) {
		this.timingDate = timingDate;
	}

	public Integer getPubStatus() {
		return pubStatus;
	}

	public void setPubStatus(Integer pubStatus) {
		this.pubStatus = pubStatus;
	}
}