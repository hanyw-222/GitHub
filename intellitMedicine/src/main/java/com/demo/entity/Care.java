package com.demo.entity;
public class Care {

    private int careId;
    private int hosId;

    private String careName;
    private String phone;
    private String sex;
    private String skill;
    private String headAdd;

    public int getCareId() {
        return careId;
    }

    public void setCareId(int careId) {
        this.careId = careId;
    }

    public int getHosId() {
        return hosId;
    }

    public void setHosId(int hosId) {
        this.hosId = hosId;
    }

    public String getCareName() {
        return careName;
    }

    public void setCareName(String careName) {
        this.careName = careName;
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

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getHeadAdd() {
        return headAdd;
    }

    public void setHeadAdd(String headAdd) {
        this.headAdd = headAdd;
    }

    @Override
    public String toString() {
        return "care{" +
                "careId=" + careId +
                ", hosId=" + hosId +
                ", careName='" + careName + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", skill='" + skill + '\'' +
                ", headAdd='" + headAdd + '\'' +
                '}';
    }
}
