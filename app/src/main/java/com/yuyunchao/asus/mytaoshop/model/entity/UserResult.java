package com.yuyunchao.asus.mytaoshop.model.entity;

/**
 * Created by asus on 2016/11/18.
 */
public class UserResult {
    private int code;
    private String msg;
    private UserEntity data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserEntity getData() {
        return data;
    }

    public void setData(UserEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
