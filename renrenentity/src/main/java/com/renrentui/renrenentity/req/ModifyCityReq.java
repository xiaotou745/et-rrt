package com.renrentui.renrenentity.req;

public class ModifyCityReq {
	private String openPublicCityCodeList;
	
	private String closePublicCityCodeList;

	private String openHotCityCodeList;
	
	private String closeHotCityCodeList;

	public String getClosePublicCityCodeList() {
		return closePublicCityCodeList;
	}

	public void setClosePublicCityCodeList(String closePublicCityCodeList) {
		this.closePublicCityCodeList = closePublicCityCodeList;
	}

	public String getOpenHotCityCodeList() {
		return openHotCityCodeList;
	}

	public void setOpenHotCityCodeList(String openHotCityCodeList) {
		this.openHotCityCodeList = openHotCityCodeList;
	}

	public String getCloseHotCityCodeList() {
		return closeHotCityCodeList;
	}

	public void setCloseHotCityCodeList(String closeHotCityCodeList) {
		this.closeHotCityCodeList = closeHotCityCodeList;
	}

	public String getOpenPublicCityCodeList() {
		return openPublicCityCodeList;
	}

	public void setOpenPublicCityCodeList(String openPublicCityCodeList) {
		this.openPublicCityCodeList = openPublicCityCodeList;
	}
}
