package com.yuyunchao.asus.mytaoshop.presenter.main.shop.viewholder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.presenter.main.shop.GoodsRecommendAdapter;
import com.yuyunchao.asus.mytaoshop.utils.myview.MyViewPager;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by asus on 2016/11/22.
 */
public class PagerViewHolder extends RecyclerView.ViewHolder implements GoodsRecommendAdapter.OnItemClickListener{
    public int currItem;
    public Context context;
    public MyViewPager vp;
    public CircleIndicator indicator;
    public ArrayList<ImageView> imgViewList;
    public GoodsRecommendAdapter adapter;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 11){
                if (currItem == imgViewList.size()) {
                    currItem = 0;
                    vp.setCurrentItem(currItem,false);
                }
                else {
                    vp.setCurrentItem(currItem++);
                }
            }
            sendEmptyMessageDelayed(11, 2000);
        }
    };

    public PagerViewHolder(View itemView,ArrayList<ImageView> list,Context context) {
        super(itemView);
        imgViewList = list;
        this.context = context;
        vp = (MyViewPager) itemView.findViewById(R.id.vp_recommends);
        indicator = (CircleIndicator) itemView.findViewById(R.id.ci_recommend);
        init();
    }
    public void init(){
        adapter = new GoodsRecommendAdapter(imgViewList);
        vp.setAdapter(adapter);
        adapter.setListener(this);
        indicator.setViewPager(vp);
        handler.sendEmptyMessageDelayed(11, 2000);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setNestParent((ViewGroup) vp.getParent());
    }

    @Override
    public void onItemClick(View v) {
        Toast.makeText(context, v.getTag() + "", Toast.LENGTH_SHORT).show();
    }
}
