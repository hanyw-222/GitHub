package com.demo.entity;

import java.util.Date;

public class Doctor {

    private int doctorId;
    private int hosId;

    private String birthday;
    private String doctorName;
    private String password;
    private String phone;
    private String sex;
    private String department;
    private String cardId;
    private String headAdd;
    private String certAdd;
    private String homeAdd;
    private String graSchool;
    private String visitCount;
    private String check_2;
    private int roleType;
    private String title;
//    private String date;

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", hosId=" + hosId +
                ", birthday='" + birthday + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", department='" + department + '\'' +
                ", cardId='" + cardId + '\'' +
                ", headAdd='" + headAdd + '\'' +
                ", certAdd='" + certAdd + '\'' +
                ", homeAdd='" + homeAdd + '\'' +
                ", graSchool='" + graSchool + '\'' +
                ", visitCount='" + visitCount + '\'' +
                ", check_2='" + check_2 + '\'' +
                ", roleType=" + roleType +
                ", title='" + title + '\'' +
                '}';
    }

    public String getBirthday() {
        return birthday;
    }

//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getHosId() {
        return hosId;
    }

    public void setHosId(int hosId) {
        this.hosId = hosId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getHeadAdd() {
        return headAdd;
    }

    public void setHeadAdd(String headAdd) {
        this.headAdd = headAdd;
    }

    public String getCertAdd() {
        return certAdd;
    }

    public void setCertAdd(String certAdd) {
        this.certAdd = certAdd;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getGraSchool() {
        return graSchool;
    }

    public void setGraSchool(String graSchool) {
        this.graSchool = graSchool;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }

    public String getCheck_2() {
        return check_2;
    }

    public void setCheck_2(String check_2) {
        this.check_2 = check_2;
    }

}
