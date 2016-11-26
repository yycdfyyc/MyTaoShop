package com.yuyunchao.asus.mytaoshop.network;


import com.google.gson.Gson;
import com.yuyunchao.asus.mytaoshop.model.entity.UserEntity;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by asus on 2016/11/16.
 */

public class ShopClient {

    private OkHttpClient okHttpClient;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private ShopClient() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)  // 连接超时
                .writeTimeout(10, TimeUnit.SECONDS)    // Socket写超时
                .readTimeout(30, TimeUnit.SECONDS)     // Socket读超时
                .build();
    }

    private static ShopClient shopClient;

    public static ShopClient getInstance() {
        if (shopClient == null) {
            shopClient = new ShopClient();
        }
        return shopClient;
    }


    /**
     * 获取所有商品
     */
    public Call getData(int pageNo, String type){
        RequestBody requestBody = new FormBody.Builder()
                .add("pageNo", String.valueOf(pageNo))
                .add("type", type)
                .build();
        Request request = new Request.Builder()
                .url(ServiceConstant.BASE_URL + ServiceConstant.GOODS_BASE_URL+ServiceConstant.GOODS_ALL_URL)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }

    /**
     * 获取商品详细信息
     */
    public Call getGoodsData(String uuid){
        RequestBody requestBody = new FormBody.Builder()
                .add("uuid", uuid)
                .build();
        Request request = new Request.Builder()
                .url(ServiceConstant.BASE_URL + ServiceConstant.GOODS_BASE_URL+ServiceConstant.GOODS_VIEW_URL)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }

    /**
     * 用户注册
     */
    public Call userRegister(String name,String pwd){
        RequestBody requestBody = new FormBody.Builder()
                .add("username", name)
                .add("password", pwd)
                .build();
        Request request = new Request.Builder()
                .url(ServiceConstant.BASE_URL + ServiceConstant.USER_BASE_URL+ServiceConstant.REGISTER_URL)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }

    /**
     * 用户登录
     */
    public Call userLogin(String name,String pwd){
        RequestBody requestBody = new FormBody.Builder()
                .add("username", name)
                .add("password", pwd)
                .build();
        Request request = new Request.Builder()
                .url(ServiceConstant.BASE_URL + ServiceConstant.USER_BASE_URL+ServiceConstant.LOGIN_URL)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }

    /**
     * 用户信息更新
     */
    public Call userUpdata(UserEntity entity, File file){
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("user", new Gson().toJson(entity))
                .addFormDataPart("image", file.getName(),RequestBody.create(MediaType.parse("image/*"), file));
        MultipartBody multipartBody = builder.build();
        Request request = new Request.Builder()
                .url(ServiceConstant.BASE_URL + ServiceConstant.USER_BASE_URL+ServiceConstant.UPDATE_URL)
                .post(multipartBody)
                .build();
        return okHttpClient.newCall(request);
    }

    /**
     * 头像上传
     * @param imgurl
     * @return
     */
    public Call upHead(String imgurl){
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File file = new File(imgurl);
        if (file != null) {
            builder.addFormDataPart(
                    "img",
                    file.getName(),
                    RequestBody.create(MediaType.parse("image/*"), file));
        }
        MultipartBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(ServiceConstant.BASE_URL + ServiceConstant.USER_BASE_URL+ServiceConstant.UPDATE_URL)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }


}

