package com.demo.entity;

public class record {

    private int recId;
    private int doctorId;
    private int userId;
    private String visitData;
    private String recTime;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVisitData() {
        return visitData;
    }

    public void setVisitData(String visitData) {
        this.visitData = visitData;
    }

    public String getRecTime() {
        return recTime;
    }

    public void setRecTime(String recTime) {
        this.recTime = recTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recId=" + recId +
                ", doctorId=" + doctorId +
                ", userId=" + userId +
                ", visitData='" + visitData + '\'' +
                ", recTime='" + recTime + '\'' +
                '}';
    }
}
