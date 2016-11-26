package com.yuyunchao.asus.mytaoshop.model.event;

/**
 * Created by asus on 2016/11/25.
 */
public class Login {
    private boolean islogin;

    public Login(boolean islogin) {
        this.islogin = islogin;
    }

    public boolean islogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }
}
