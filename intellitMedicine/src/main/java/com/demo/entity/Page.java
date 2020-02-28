package com.demo.entity;

import java.util.List;

public class Page {
    /**
     * 当前页
     */
    private int curPage;
    /**
     * 最大页数
     */
    private int maxPage;
    /**
     * 每页行数
     */
    private int row4Page;
    /**
     * 返回的数据
     */
    private List<Object> objList;

    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
    public int getMaxPage() {
        return maxPage;
    }
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    public int getRow4Page() {
        return row4Page;
    }
    public void setRow4Page(int row4Page) {
        this.row4Page = row4Page;
    }
    public List<Object> getObjList() {
        return objList;
    }
    public void setObjList(List<Object> objList) {
        this.objList = objList;
    }
    @Override
    public String toString() {
        return "Page{" +
                "curPage=" + curPage +
                ", maxPage=" + maxPage +
                ", row4Page=" + row4Page +
                ", objList=" + objList +
                '}';
    }
}
