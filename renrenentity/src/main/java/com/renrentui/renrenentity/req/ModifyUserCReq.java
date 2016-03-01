package com.renrentui.renrenentity.req;

/**
 * C端修改个人基础信息
 * @author CaoHeYang
 * @date 20151008
 */
public class ModifyUserCReq {
	private long userId;
	private String userName;
	private  Short sex;
	private  Integer age;
	private String headImage;
	private String birthDay;
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	/**
	 * 用户ID
	 * @return
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * 用户ID
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * 用户姓名
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 用户姓名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 性别 2女默认1男
	 * @return
	 */
	public Short getSex() {
		return sex;
	}
	/**
	 * 性别 2女默认1男
	 * @param sex
	 */
	public void setSex(Short sex) {
		this.sex = sex;
	}
	/**
	 * 年龄
	 * @return
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 年龄
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

}
