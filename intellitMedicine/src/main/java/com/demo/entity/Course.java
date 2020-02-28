package com.demo.entity;

public class Course {

    private int courseId;
    private String courseName;
    private String courseType;
    private int coursePro;
    private String courseIntro;
    private String courseAdd;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getCoursePro() {
        return coursePro;
    }

    public void setCoursePro(int coursePro) {
        this.coursePro = coursePro;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public String getCourseAdd() {
        return courseAdd;
    }

    public void setCourseAdd(String courseAdd) {
        this.courseAdd = courseAdd;
    }

    @Override
    public String toString() {
        return "course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", coursePro=" + coursePro +
                ", courseIntro='" + courseIntro + '\'' +
                ", courseAdd='" + courseAdd + '\'' +
                '}';
    }
}
