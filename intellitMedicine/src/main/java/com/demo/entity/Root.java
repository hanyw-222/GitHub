package com.demo.entity;

public class Root {
    private int rootId;
    private String rootName;
    private String password;
    private int roleType;

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Root{" +
                "rootId=" + rootId +
                ", rootName='" + rootName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
