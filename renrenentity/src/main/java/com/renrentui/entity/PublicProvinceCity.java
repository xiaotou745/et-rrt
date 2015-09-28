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

    
}