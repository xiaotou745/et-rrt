package com.renrentui.entity;

import java.util.Date;

public class Clienter {
    private Long id;

    private String clientername;

    private String phoneno;

    private String password;

    private String loginname;

    private String headimage;

    private String cityname;

    private Integer citycode;

    private Short sex;

    private Integer age;

    private String education;

    private Date lastlogintime;

    private Date createtime;

    private Integer status;

    private String lastoptname;

    private Date lastopttime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientername() {
        return clientername;
    }

    public void setClientername(String clientername) {
        this.clientername = clientername == null ? null : clientername.trim();
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage == null ? null : headimage.trim();
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public Integer getCitycode() {
        return citycode;
    }

    public void setCitycode(Integer citycode) {
        this.citycode = citycode;
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

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLastoptname() {
        return lastoptname;
    }

    public void setLastoptname(String lastoptname) {
        this.lastoptname = lastoptname == null ? null : lastoptname.trim();
    }

    public Date getLastopttime() {
        return lastopttime;
    }

    public void setLastopttime(Date lastopttime) {
        this.lastopttime = lastopttime;
    }
}