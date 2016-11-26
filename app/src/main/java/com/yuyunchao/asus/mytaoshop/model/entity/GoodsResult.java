package com.yuyunchao.asus.mytaoshop.model.entity;

import java.util.ArrayList;

/**
 * Created by asus on 2016/11/17.
 */
public class GoodsResult {
    private int code;
    private String msg;
    private ArrayList<GoodsEntity> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<GoodsEntity> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<GoodsEntity> datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
