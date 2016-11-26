package com.yuyunchao.asus.mytaoshop.presenter.main.shop;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by asus on 2016/11/21.
 */
public class GoodsRecommendAdapter extends PagerAdapter {
    private ArrayList<ImageView> list;
    private OnItemClickListener listener;

    public GoodsRecommendAdapter(ArrayList<ImageView> list) {
        this.list = list;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView imageView = list.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("yyc", "" + imageView.getTag());
                if (listener != null) {
                    listener.onItemClick(v);
                }
            }
        });
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnItemClickListener {
        void onItemClick(View v);
    }
}
