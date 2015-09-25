package com.renrentui.entity;

public class PublicProvinceCity {
    private Integer id;

    private String name;

    private Integer code;

    private Integer parentcode;

    private Integer jibie;

    private String loweracronym;

    private String upperacronym;

    private String lowerfullpinyin;

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

    public Integer getParentcode() {
        return parentcode;
    }

    public void setParentcode(Integer parentcode) {
        this.parentcode = parentcode;
    }

    public Integer getJibie() {
        return jibie;
    }

    public void setJibie(Integer jibie) {
        this.jibie = jibie;
    }

    public String getLoweracronym() {
        return loweracronym;
    }

    public void setLoweracronym(String loweracronym) {
        this.loweracronym = loweracronym == null ? null : loweracronym.trim();
    }

    public String getUpperacronym() {
        return upperacronym;
    }

    public void setUpperacronym(String upperacronym) {
        this.upperacronym = upperacronym == null ? null : upperacronym.trim();
    }

    public String getLowerfullpinyin() {
        return lowerfullpinyin;
    }

    public void setLowerfullpinyin(String lowerfullpinyin) {
        this.lowerfullpinyin = lowerfullpinyin == null ? null : lowerfullpinyin.trim();
    }
}