package com.yuyunchao.asus.mytaoshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yuyunchao.asus.mytaoshop.model.entity.UserEntity;

/**
 * Created by asus on 2016/11/24.
 */
public class CacheUserPreferences {
    private static String HEAD_URL = "head_url"; //头像路径
    private static String HX_ID  = "hx_id";//环信ID
    private static String NI_NAME = "ni_name";//昵称
    private static String UUID = "uuid";//用户表中主键
    private static String USER_NAME  = "user_name";//用户名
    private static String PWD = "password";//密码
    public static boolean isLogin;
    public static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private CacheUserPreferences(){}
    public static void init(Context context){
        preferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    public static void setUser(UserEntity entity){
        isLogin = true;
        editor.putString(HEAD_URL, entity.getOther());
        editor.putString(HX_ID, entity.getName());
        editor.putString(NI_NAME, entity.getNickname());
        editor.putString(UUID, entity.getUuid());
        editor.putString(USER_NAME, entity.getUsername());
        editor.putString(PWD, entity.getPassword());
        editor.commit();
    }
    public static UserEntity getUser(){
        UserEntity entity = new UserEntity();
        entity.setOther(preferences.getString(HEAD_URL,""));
        entity.setName(preferences.getString(HX_ID,""));
        entity.setNickname(preferences.getString(NI_NAME,""));
        entity.setUuid(preferences.getString(UUID,""));
        entity.setUsername(preferences.getString(USER_NAME,""));
        entity.setPassword(preferences.getString(PWD,""));
        return entity;
    }

}
