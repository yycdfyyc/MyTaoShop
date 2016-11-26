package com.yuyunchao.asus.mytaoshop.presenter.main.shop;

import com.yuyunchao.asus.mytaoshop.model.entity.GoodsEntity;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/11/16.
 */
public interface GoodsView extends MvpView {
    GoodsView NULL = new GoodsView() {

        @Override
        public void hideRefresh() {

        }

        @Override
        public void addRefreshData(ArrayList<GoodsEntity> data) {

        }

        @Override
        public void loadMoreData(ArrayList<GoodsEntity> data) {

        }



        @Override
        public void loadDataError() {

        }
    };

    /**
     * 数据刷新 --隐藏下拉刷新视图
     */
    void hideRefresh();

    /**
     * 添加刷新更多的数据
     */
    void addRefreshData(ArrayList<GoodsEntity> data);

    /**
     * 添加更多数据
     */
    void loadMoreData(ArrayList<GoodsEntity> data);

    /**
     * 添加数据失败
     */
    void loadDataError();
}
