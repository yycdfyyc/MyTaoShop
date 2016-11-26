package com.yuyunchao.asus.mytaoshop.presenter.main.shop;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyunchao.asus.mytaoshop.R;


/**
 * Created by asus on 2016/11/17.
 */
public class GoodsTypeAdapter extends RecyclerView.Adapter<GoodsTypeAdapter.MyViewHolder> {
    private String[] types = new String[]{"全部","家用","电子","服饰","玩具","图书","礼品","其他"};
    private int nowPosition;
    private int[] imgsId = new int[]{
            R.drawable.img_all,R.drawable.img_household,R.drawable.img_electron,R.drawable.img_dress,
            R.drawable.img_toys,R.drawable.img_book,R.drawable.img_gift,R.drawable.img_other
    };
    private OnItemClickListener listener;
    public int getNowPosition() {
        return nowPosition;
    }
    public void setNowPosition(int nowPosition) {
        this.nowPosition = nowPosition;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_goods_type, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setTextColor(Color.BLACK);
        if (nowPosition == position)
            holder.tv.setTextColor(Color.BLUE);
        holder.im.setImageResource(imgsId[position]);
        View.OnClickListener s = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClickListener(position);
                }
            }
        };
        holder.im.setOnClickListener(s);
        holder.tv.setOnClickListener(s);

        holder.tv.setText(types[position]);

    }

    @Override
    public int getItemCount() {
        return types.length;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private ImageView im;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item_goods_type);
            im = (ImageView) itemView.findViewById(R.id.im_item_goods_type);
        }
    }
}
