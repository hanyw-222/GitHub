package com.demo.entity;

public class healthReport {

    private int healthRecId;
    private int userId;
    private String weight;
    private String height;
    private int smoking;
    private int drinking;
    private int diet;
    private int excretion;
    private int allergy;

    public int getHealthRecId() {
        return healthRecId;
    }

    public void setHealthRecId(int healthRecId) {
        this.healthRecId = healthRecId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getSmoking() {
        return smoking;
    }

    public void setSmoking(int smoking) {
        this.smoking = smoking;
    }

    public int getDrinking() {
        return drinking;
    }

    public void setDrinking(int drinking) {
        this.drinking = drinking;
    }

    public int getDiet() {
        return diet;
    }

    public void setDiet(int diet) {
        this.diet = diet;
    }

    public int getExcretion() {
        return excretion;
    }

    public void setExcretion(int excretion) {
        this.excretion = excretion;
    }

    public int getAllergy() {
        return allergy;
    }

    public void setAllergy(int allergy) {
        this.allergy = allergy;
    }

    @Override
    public String toString() {
        return "healthReport{" +
                "healthRecId=" + healthRecId +
                ", userId=" + userId +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", smoking=" + smoking +
                ", drinking=" + drinking +
                ", diet=" + diet +
                ", excretion=" + excretion +
                ", allergy=" + allergy +
                '}';
    }
}
