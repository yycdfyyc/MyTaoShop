package com.yuyunchao.asus.mytaoshop.model.entity;

import java.util.ArrayList;

/**
 * Created by asus on 2016/11/17.
 */
public class GoodsDetailsResult {

    private int code;
    private String msg;
    private GoodsDetailsEntity datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public GoodsDetailsEntity getDatas() {
        return datas;
    }

    public void setDatas(GoodsDetailsEntity datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
