package com.yuyunchao.asus.mytaoshop.presenter.main.myself.user.login;

import com.yuyunchao.asus.mytaoshop.model.entity.UserEntity;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpView;

/**
 * Created by asus on 2016/11/24.
 */
public interface LoginView extends MvpView {
    LoginView NULL = new LoginView() {
        @Override
        public void loginSuccess(UserEntity entity) {

        }

        @Override
        public void loginFail(String msg) {

        }
    };
    void loginSuccess(UserEntity entity);
    void loginFail(String msg);
}
