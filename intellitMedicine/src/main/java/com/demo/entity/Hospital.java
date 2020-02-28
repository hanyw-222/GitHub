package com.demo.entity;

public class Hospital {

    private int hosId;
    private String hosName;
    private String hosDepartment;
    private String hospitalGen;

    public int getHosId() {
        return hosId;
    }

    public void setHosId(int hosId) {
        this.hosId = hosId;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getHosDepartment() {
        return hosDepartment;
    }

    public void setHosDepartment(String hosDepartment) {
        this.hosDepartment = hosDepartment;
    }

    public String getHospitalGen() {
        return hospitalGen;
    }

    public void setHospitalGen(String hospitalGen) {
        this.hospitalGen = hospitalGen;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hosId=" + hosId +
                ", hosName='" + hosName + '\'' +
                ", hosDepartment='" + hosDepartment + '\'' +
                ", hospitalGen='" + hospitalGen + '\'' +
                '}';
    }
}
