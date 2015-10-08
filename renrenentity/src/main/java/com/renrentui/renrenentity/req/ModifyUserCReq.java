package com.renrentui.renrenentity.req;

/**
 * C端修改个人基础信息
 * @author CaoHeYang
 * @date 20151008
 */
public class ModifyUserCReq {
	private int userId;
	private String userName;
	private  Integer sex;
	private  Integer age;
	/**
	 * 用户ID
	 * @return
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * 用户ID
	 * @param userId
	 */
	public void setUserId(int userId) {
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
	public Integer getSex() {
		return sex;
	}
	/**
	 * 性别 2女默认1男
	 * @param sex
	 */
	public void setSex(Integer sex) {
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

}
