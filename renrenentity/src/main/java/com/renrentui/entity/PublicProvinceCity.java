package com.renrentui.entity;

public class PublicProvinceCity {
    private Integer id;

    private String name;

    private Integer code;

    private Integer parentCode;

    private Integer jiBie;

    private String loweRacronym;

    private String upperRcronym;

    private String lowerFullPinYin;

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

    
}