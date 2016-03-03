package com.renrentui.renrenentity.domain;

import java.util.Date;

import com.renrentui.renrenentity.common.ResponseBase;  
public class ClienterWxModel extends ResponseBase{
	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String openId;

	/**
	 * 微信用户号
	 */
	private String fromUserName;

	/**
	 * 微信号
	 */
	private String wxId;

	/**
	 * 关注状态：0取消关注，默认1已关注
	 */
	private Integer followStatus;

	/**
	 * 关注时间
	 */
	private Date followTime;

	/**
	 * 取消关注时间
	 */
	private Date unFollowTime;

	/**
	 * 绑定地推员ID
	 */
	private Integer clienterId;

	private String clienterPhoneNo;
	/**
	 * 获取
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置
	 * @param id 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置
	 * @param openId 
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 获取微信用户号
	 */
	public String getFromUserName() {
		return fromUserName;
	}
	/**
	 * 设置微信用户号
	 * @param fromUserName 微信用户号
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * 获取微信号
	 */
	public String getWxId() {
		return wxId;
	}
	/**
	 * 设置微信号
	 * @param wxId 微信号
	 */
	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	/**
	 * 获取关注状态：0取消关注，默认1已关注
	 */
	public Integer getFollowStatus() {
		return followStatus;
	}
	
	public String getFollowStatusString(){
		if(followStatus.equals(0)){
			return "已取消";
		}else{
			return "已关注";
		}
	}
	/**
	 * 设置关注状态：0取消关注，默认1已关注
	 * @param followStatus 关注状态：0取消关注，默认1已关注
	 */
	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}

	/**
	 * 获取关注时间
	 */
	public Date getFollowTime() {
		return followTime;
	}
	/**
	 * 设置关注时间
	 * @param followTime 关注时间
	 */
	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}

	/**
	 * 获取取消关注时间
	 */
	public Date getUnFollowTime() {
		return unFollowTime;
	}
	/**
	 * 设置取消关注时间
	 * @param unFollowTime 取消关注时间
	 */
	public void setUnFollowTime(Date unFollowTime) {
		this.unFollowTime = unFollowTime;
	}

	/**
	 * 获取绑定地推员ID
	 */
	public Integer getClienterId() {
		return clienterId;
	}
	/**
	 * 设置绑定地推员ID
	 * @param clienterId 绑定地推员ID
	 */
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	public String getClienterPhoneNo() {
		return clienterPhoneNo;
	}
	public void setClienterPhoneNo(String clienterPhoneNo) {
		this.clienterPhoneNo = clienterPhoneNo;
	}

}
