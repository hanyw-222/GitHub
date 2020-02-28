package com.demo.entity;

public class Assign {

    private int applyId;
    private int RootId;
    private int doctorId;
    private int userId;
    private String applyIntro;
    private String applyTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String is_checked;
    private User user ;

    public String getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(String is_checked) {
        this.is_checked = is_checked;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getRootId() {
        return RootId;
    }

    public void setRootId(int rootId) {
        RootId = rootId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getApplyIntro() {
        return applyIntro;
    }

    public void setApplyIntro(String applyIntro) {
        this.applyIntro = applyIntro;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    @Override
    public String toString() {
        return "Assign{" +
                "applyId=" + applyId +
                ", RootId=" + RootId +
                ", userId=" + userId +
                ", applyIntro='" + applyIntro + '\'' +
                ", applyTime='" + applyTime + '\'' +
                '}';
    }
}
