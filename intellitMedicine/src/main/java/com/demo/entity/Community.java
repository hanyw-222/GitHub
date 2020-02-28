package com.demo.entity;

public class Community {

    private int communityId;
    private String comName;
    private String comInfor;
    private String headAdd;

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComInfor() {
        return comInfor;
    }

    public void setComInfor(String comInfor) {
        this.comInfor = comInfor;
    }

    public String getHeadAdd() {
        return headAdd;
    }

    public void setHeadAdd(String headAdd) {
        this.headAdd = headAdd;
    }

    @Override
    public String toString() {
        return "community{" +
                "communityId=" + communityId +
                ", comName='" + comName + '\'' +
                ", comInfor='" + comInfor + '\'' +
                ", headAdd='" + headAdd + '\'' +
                '}';
    }
}
