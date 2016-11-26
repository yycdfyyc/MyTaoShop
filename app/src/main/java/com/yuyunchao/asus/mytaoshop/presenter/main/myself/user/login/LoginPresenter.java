package com.yuyunchao.asus.mytaoshop.presenter.main.myself.user.login;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.yuyunchao.asus.mytaoshop.model.entity.UserResult;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpPresenter;
import com.yuyunchao.asus.mytaoshop.network.ShopClient;
import com.yuyunchao.asus.mytaoshop.network.UICallback;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by asus on 2016/11/24.
 */
public class LoginPresenter extends MvpPresenter<LoginView> {
    private Call call;

    public void getLoginInfo(String username,String pwd){
        call = ShopClient.getInstance().userLogin(username, pwd);

        call.enqueue(new UICallback() {
            @Override
            public void onFailureInUi(Call call, IOException e) {
                getView().loginFail("网络连接失败");
            }

            @Override
            public void onResponseInUi(Call call, String body) {
                UserResult result = new Gson().fromJson(body, UserResult.class);
                if (result.getCode() == 1) {
                    getView().loginSuccess(result.getData());
                } else {
                    getView().loginFail("登录失败");
                }
            }
        });
    }

    @NonNull
    @Override
    protected LoginView getNullObject() {
        return LoginView.NULL;
    }
}
