package com.renrentui.renrenentity.domain;

import java.util.List;

import com.renrentui.renrenentity.PublicProvinceCity;

public class RegionModelFirstLetter {
	/*
	 * 首字母
	 */
	private String firstLetter;
	/*
	 * 城市信息
	 */
	private List<PublicProvinceCity> regionModel;

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public List<PublicProvinceCity> getRegionModel() {
		return regionModel;
	}

	public void setRegionModel(List<PublicProvinceCity> regionModel) {
		this.regionModel = regionModel;
	}
	
}
