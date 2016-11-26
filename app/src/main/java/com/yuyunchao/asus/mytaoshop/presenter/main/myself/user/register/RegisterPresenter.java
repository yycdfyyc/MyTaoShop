package com.yuyunchao.asus.mytaoshop.presenter.main.myself.user.register;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.yuyunchao.asus.mytaoshop.model.entity.UserEntity;
import com.yuyunchao.asus.mytaoshop.model.entity.UserResult;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpPresenter;
import com.yuyunchao.asus.mytaoshop.network.ShopClient;
import com.yuyunchao.asus.mytaoshop.network.UICallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

/**
 * Created by asus on 2016/11/24.
 */
public class RegisterPresenter extends MvpPresenter<RegisterView> {
    private Call call;

    public void getRegisterInfo(String username,String pwd){
        call = ShopClient.getInstance().userRegister(username, pwd);
        call.enqueue(new UICallback() {
            @Override
            public void onFailureInUi(Call call, IOException e) {

            }

            @Override
            public void onResponseInUi(Call call, String body) {
                UserResult result = new Gson().fromJson(body, UserResult.class);
                if (result.getCode() == 1) {
                    getView().registerSuccess(result.getData());
                }else if (result.getCode()==2){
                    getView().registerFail(result.getMsg());
                }
            }
        });
    }

    public void getUpdataInfo(UserEntity entity, File file) {
        call = ShopClient.getInstance().userUpdata(entity,file);
        Log.i("yyc", ">>>>>>>>");
        call.enqueue(new UICallback() {
            @Override
            public void onFailureInUi(Call call, IOException e) {
                getView().upDataFail("获取数据失败");
            }

            @Override
            public void onResponseInUi(Call call, String body) {
                UserResult result = new Gson().fromJson(body, UserResult.class);
                if (result.getCode()==1){
                    getView().upDataSuccess(result.getData());
                }else {
                    getView().upDataFail(result.getMsg());
                }
            }
        });
    }

    @NonNull
    @Override
    protected RegisterView getNullObject() {
        return RegisterView.NULL;
    }
}
