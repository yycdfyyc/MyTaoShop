package com.yuyunchao.asus.mytaoshop.model.entity;

import java.io.Serializable;

/**
 * Created by asus on 2016/11/18.
 */
public class UserEntity implements Serializable{
    private String other; //头像路径
    private String name;//环信ID
    private String nickname;//昵称
    private String uuid;//用户表中主键
    private String username;//用户名
    private String password;//密码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
