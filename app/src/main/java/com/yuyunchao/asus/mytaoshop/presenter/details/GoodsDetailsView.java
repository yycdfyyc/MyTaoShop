package com.yuyunchao.asus.mytaoshop.presenter.details;

import com.yuyunchao.asus.mytaoshop.model.entity.GoodsDetailsEntity;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpView;

import java.util.ArrayList;

/**
 * Created by asus on 2016/11/17.
 */
public interface GoodsDetailsView extends MvpView {
    GoodsDetailsView Null = new GoodsDetailsView(){

        @Override
        public void setTextData(GoodsDetailsEntity entity) {

        }

        @Override
        public void setImgData(ArrayList<String> list) {

        }
    };

    /**
     * 设置文本的数据
     * @param entity
     */
    void setTextData(GoodsDetailsEntity entity);

    /**
     * 设置图片数据
     * @param imgUrl
     */
    void setImgData(ArrayList<String> imgUrl);
}
