package com.yuyunchao.asus.mytaoshop.presenter.details;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.yuyunchao.asus.mytaoshop.model.entity.GoodsDetailsEntity;
import com.yuyunchao.asus.mytaoshop.model.entity.GoodsDetailsResult;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpPresenter;
import com.yuyunchao.asus.mytaoshop.network.ShopClient;
import com.yuyunchao.asus.mytaoshop.network.UICallback;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by asus on 2016/11/17.
 */
public class GoodsDetailsPresenter extends MvpPresenter<GoodsDetailsView> {

    private Call call;

    public void getData(String uuid){
        call = ShopClient.getInstance().getGoodsData(uuid);
        call.enqueue(new UICallback() {
            @Override
            public void onFailureInUi(Call call, IOException e) {

            }

            @Override
            public void onResponseInUi(Call call, String body) {
                GoodsDetailsResult result = new Gson().fromJson(body, GoodsDetailsResult.class);
                if (result.getCode() == 1) {
                    GoodsDetailsEntity goodsDetailsEntity = result.getDatas();
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < goodsDetailsEntity.getPages().size(); i++) {
                        list.add(goodsDetailsEntity.getPages().get(i).getUri());
                    }
                    getView().setImgData(list);
                    getView().setTextData(goodsDetailsEntity);
                }
            }
        });
    }

    @NonNull
    @Override
    protected GoodsDetailsView getNullObject() {
        return GoodsDetailsView.Null;
    }
}
