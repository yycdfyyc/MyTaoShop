package com.yuyunchao.asus.mytaoshop.presenter.main.shop.viewholder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.presenter.main.shop.GoodsFragment;

/**
 * Created by asus on 2016/11/22.
 */
public class TypeViewHolder extends RecyclerView.ViewHolder{
    private RecyclerView rv;
    public TypeViewHolder(View itemView, Context context) {
        super(itemView);
        rv = (RecyclerView) itemView.findViewById(R.id.rv_item_type);
        rv.setLayoutManager(new GridLayoutManager(context,4));
        rv.setAdapter(GoodsFragment.typeAdapter);
    }

}
