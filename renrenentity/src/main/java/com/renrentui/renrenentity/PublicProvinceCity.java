package com.renrentui.renrenentity;

import java.io.Serializable;

public class PublicProvinceCity implements Serializable {
    private Integer id;

    private String name;

    private Integer code;

    private Integer parentCode;

    private Integer jiBie;

    private String loweRacronym;

    private String upperRcronym;

    private String lowerFullPinYin;
    /*
     * 首字母
     */
    private String firstLetter;
    /*
     * 是否热门城市
     */
    private Integer isHot;

    private Integer isPublic;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getJiBie() {
		return jiBie;
	}

	public void setJiBie(Integer jiBie) {
		this.jiBie = jiBie;
	}

	public String getLoweRacronym() {
		return loweRacronym;
	}

	public void setLoweRacronym(String loweRacronym) {
		this.loweRacronym = loweRacronym;
	}

	public String getUpperRcronym() {
		return upperRcronym;
	}

	public void setUpperRcronym(String upperRcronym) {
		this.upperRcronym = upperRcronym;
	}

	public String getLowerFullPinYin() {
		return lowerFullPinYin;
	}

	public void setLowerFullPinYin(String lowerFullPinYin) {
		this.lowerFullPinYin = lowerFullPinYin;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

    
}