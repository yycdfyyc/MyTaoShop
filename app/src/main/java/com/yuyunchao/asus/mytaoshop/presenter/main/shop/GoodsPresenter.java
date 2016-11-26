package com.yuyunchao.asus.mytaoshop.presenter.main.shop;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.yuyunchao.asus.mytaoshop.model.entity.GoodsResult;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpPresenter;
import com.yuyunchao.asus.mytaoshop.network.ShopClient;
import com.yuyunchao.asus.mytaoshop.network.UICallback;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by asus on 2016/11/16.
 */
public class GoodsPresenter extends MvpPresenter<GoodsView>{
    private Call call;
    /**
     * 获取商品时,分页下标
     */
    private int pageInt = 1;

    public void getRefalshData(int pageNo,String type){
        call = ShopClient.getInstance().getData(pageNo,type);

        call.enqueue(new UICallback() {
            @Override
            public void onFailureInUi(Call call, IOException e) {

            }

            @Override
            public void onResponseInUi(Call call, String body) {
                GoodsResult goodsResult = new Gson().fromJson(body, GoodsResult.class);
                if (goodsResult.getCode() == 1) {
                    getView().addRefreshData(goodsResult.getDatas());
                    getView().hideRefresh();
                    pageInt=2;
                }
            }
        });
    }
    public void getLoadMoreData(String type){
        call = ShopClient.getInstance().getData(pageInt,type);

        call.enqueue(new UICallback() {
            @Override
            public void onFailureInUi(Call call, IOException e) {

            }

            @Override
            public void onResponseInUi(Call call, String body) {
                GoodsResult goodsResult = new Gson().fromJson(body, GoodsResult.class);
                if (goodsResult.getCode() == 1&&goodsResult.getDatas()!=null&&!goodsResult.getDatas().equals("")) {
                    getView().loadMoreData(goodsResult.getDatas());
                    getView().hideRefresh();
                    pageInt++;
                }
            }
        });
    }

    @NonNull
    @Override
    protected GoodsView getNullObject() {
        return GoodsView.NULL;
    }

}
