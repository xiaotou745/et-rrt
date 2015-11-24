package com.renrentui.renrenentity.domain;

import java.util.List;

import com.renrentui.renrenentity.PublicProvinceCity;

public class RegionModel {
	
	/*
	 * 热门城市
	 */
	private List<PublicProvinceCity> hotRegionModel;
	/*
	 * 按照首字母排序的城市
	 */
	private List<RegionModelFirstLetter> firstLetterRegionModel;
	
	private String version;
	public List<PublicProvinceCity> getHotRegionModel() {
		return hotRegionModel;
	}
	public void setHotRegionModel(List<PublicProvinceCity> hotRegionModel) {
		this.hotRegionModel = hotRegionModel;
	}
	public List<RegionModelFirstLetter> getFirstLetterRegionModel() {
		return firstLetterRegionModel;
	}
	public void setFirstLetterRegionModel(List<RegionModelFirstLetter> firstLetterRegionModel) {
		this.firstLetterRegionModel = firstLetterRegionModel;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
