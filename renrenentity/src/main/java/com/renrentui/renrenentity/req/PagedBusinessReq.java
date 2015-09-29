package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;


public class PagedBusinessReq extends PagedRequestBase{

    private String companyName;

    private String phoneNo;

    private String loginName;

    private String cityName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}   
    
}
