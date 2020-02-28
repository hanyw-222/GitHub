package com.demo.entity;

public class Pension {

    private int pensionId;
    private String pensionName;
    private String pensionIntro;
    private String headAdd;

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }

    public String getPensionName() {
        return pensionName;
    }

    public void setPensionName(String pensionName) {
        this.pensionName = pensionName;
    }

    public String getPensionIntro() {
        return pensionIntro;
    }

    public void setPensionIntro(String pensionIntro) {
        this.pensionIntro = pensionIntro;
    }

    public String getHeadAdd() {
        return headAdd;
    }

    public void setHeadAdd(String headAdd) {
        this.headAdd = headAdd;
    }

    @Override
    public String toString() {
        return "Pension{" +
                "pensionId=" + pensionId +
                ", pensionName='" + pensionName + '\'' +
                ", pensionIntro='" + pensionIntro + '\'' +
                ", headAdd='" + headAdd + '\'' +
                '}';
    }
}
