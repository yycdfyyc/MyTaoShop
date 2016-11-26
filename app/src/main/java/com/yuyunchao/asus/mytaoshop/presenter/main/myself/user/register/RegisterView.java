package com.yuyunchao.asus.mytaoshop.presenter.main.myself.user.register;


import com.yuyunchao.asus.mytaoshop.model.entity.UserEntity;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpView;

/**
 * Created by asus on 2016/11/24.
 */
public interface RegisterView extends MvpView{
    RegisterView NULL = new RegisterView() {
        @Override
        public void registerSuccess(UserEntity entity) {

        }

        @Override
        public void registerFail(String msg) {

        }

        @Override
        public void upDataSuccess(UserEntity entity) {

        }

        @Override
        public void upDataFail(String msg) {

        }
    };
    void registerSuccess(UserEntity entity);
    void registerFail(String msg);
    void upDataSuccess(UserEntity entity);
    void upDataFail(String msg);
}
