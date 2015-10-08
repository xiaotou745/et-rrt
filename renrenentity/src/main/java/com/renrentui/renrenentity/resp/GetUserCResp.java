package com.renrentui.renrenentity.resp;
/**
* @Des 用户详细信息实体类 
* @Author WangXuDan
* @Date 2015年9月30日15:09:39
* @Return
*/
public class GetUserCResp {
	private Long userId;

    private String userName;

    private String phoneNo;

    private String headImage;

    private String cityName;

    private Integer cityCode;

    private Short sex;

    private Integer age;

    private String education;

    private Integer status;

    private Double balance;

    private Double withdraw;

    private Double hadWithdraw;
    
    private Double checking;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Double withdraw) {
		this.withdraw = withdraw;
	}

	public Double getHadWithdraw() {
		return hadWithdraw;
	}

	public void setHadWithdraw(Double hadWithdraw) {
		this.hadWithdraw = hadWithdraw;
	}

	public Double getChecking() {
		return checking;
	}

	public void setChecking(Double checking) {
		this.checking = checking;
	}

}
