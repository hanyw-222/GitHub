package com.demo.entity;

import java.util.Date;

public class Onlineconsultingrec {

    private int inquiryId;

    private int userId ;

    private int doctorId;

    private Date inquiryTime;

    private String inquiryData;

    private String onlineAskPaper;

    private String state;
    private String replyData;

    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor= doctor;
    }

    public String getReplyData() {
        return replyData;
    }

    public void setReplyData(String replyData) {
        this.replyData = replyData;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(int inquiryId) {
        this.inquiryId = inquiryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getInquiryTime() {
        return inquiryTime;
    }

    public void setInquiryTime(Date inquiryTime) {
        this.inquiryTime = inquiryTime;
    }

    public String getInquiryData() {
        return inquiryData;
    }

    public void setInquiryData(String inquiryData) {
        this.inquiryData = inquiryData;
    }

    public String getOnlineAskPaper() {
        return onlineAskPaper;
    }

    public void setOnlineAskPaper(String onlineAskPaper) {
        this.onlineAskPaper = onlineAskPaper;
    }

    @Override
    public String toString() {
        return "Onlineconsultingrec{" +
                "inquiryId=" + inquiryId +
                ", userId=" + userId +
                ", doctorId=" + doctorId +
                ", inquiryTime=" + inquiryTime +
                ", inquiryData='" + inquiryData + '\'' +
                ", onlineAskPaper='" + onlineAskPaper + '\'' +
                '}';
    }
}
