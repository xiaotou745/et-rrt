package com.renrentui.renrenentity;

import java.util.Date;

public class Clienter {
    private Long id;

    private String clienterName;

    private String phoneNo;

    private String passWord;

    private String loginName;

    private String headImage;

    private String cityName;

    private Integer cityCode;

    private Short sex;

    private Integer age;

    private String education;

    private Date lastLoginTime;

    private Date createTime;

    private Integer status;

    private String lastOptName;

    private Date lastOptTime;
    private String recommendPhone;
    public String getRecommendPhone() {
		return recommendPhone;
	}

	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    

    public String getClienterName() {
		return clienterName;
	}

	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastOptName() {
		return lastOptName;
	}

	public void setLastOptName(String lastOptName) {
		this.lastOptName = lastOptName;
	}

	public Date getLastOptTime() {
		return lastOptTime;
	}

	public void setLastOptTime(Date lastOptTime) {
		this.lastOptTime = lastOptTime;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
}