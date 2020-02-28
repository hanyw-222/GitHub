package com.demo.entity;

import java.util.Date;

public class User {

    private int userId;
    private int communityId;
    private String userName;
    private String password;
    private String phone;
    private String sex;
    private String headAdd;
    private String year;
    private Date date;
    private String homeAdd;
    private String check_1;
    private int roleType;

    private Community community;

    public Community getCommunity() {
        return community;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", communityId=" + communityId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", headAdd='" + headAdd + '\'' +
                ", year='" + year + '\'' +
                ", date=" + date +
                ", homeAdd='" + homeAdd + '\'' +
                ", check_1='" + check_1 + '\'' +
                ", roleType=" + roleType +
                ", community=" + community +
                ", onlineconsultingrec=" + onlineconsultingrec +
                '}';
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    private Onlineconsultingrec onlineconsultingrec;

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }


    public Onlineconsultingrec getOnlineconsultingrec() {
        return onlineconsultingrec;
    }

    public void setOnlineconsultingrec(Onlineconsultingrec onlineconsultingrec) {
        this.onlineconsultingrec = onlineconsultingrec;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadAdd() {
        return headAdd;
    }

    public void setHeadAdd(String headAdd) {
        this.headAdd = headAdd;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getCheck_1() {
        return check_1;
    }

    public void setCheck_1(String check_1) {
        this.check_1 = check_1;
    }

}
