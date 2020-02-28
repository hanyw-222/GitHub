package com.demo.entity;

public class userAgeCount {

    private int Low30 ;

    private int _30_45;

    private int _45_60;

    private int _60_75;

    private int _75_90;

    private int Up90;

    public int getLow30() {
        return Low30;
    }

    public void setLow30(int low30) {
        Low30 = low30;
    }

    public int get_30_45() {
        return _30_45;
    }

    public void set_30_45(int _30_45) {
        this._30_45 = _30_45;
    }

    public int get_45_60() {
        return _45_60;
    }

    public void set_45_60(int _45_60) {
        this._45_60 = _45_60;
    }

    public int get_60_75() {
        return _60_75;
    }

    public void set_60_75(int _60_75) {
        this._60_75 = _60_75;
    }

    public int get_75_90() {
        return _75_90;
    }

    public void set_75_90(int _75_90) {
        this._75_90 = _75_90;
    }

    public int getUp90() {
        return Up90;
    }

    @Override
    public String toString() {
        return "userAgeCount{" +
                "Low30=" + Low30 +
                ", _30_45=" + _30_45 +
                ", _45_60=" + _45_60 +
                ", _60_75=" + _60_75 +
                ", _75_90=" + _75_90 +
                ", Up90=" + Up90 +
                '}';
    }

    public void setUp90(int up90) {
        Up90 = up90;
    }
}
