package com.yuyunchao.asus.mytaoshop.presenter.main.shop.goodstype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.entity.GoodsEntity;
import com.yuyunchao.asus.mytaoshop.network.ServiceConstant;
import com.yuyunchao.asus.mytaoshop.utils.ImageLoderOptions;
import com.yuyunchao.asus.mytaoshop.presenter.main.shop.viewholder.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/11/16.
 */
public class MyGoodsTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements ServiceConstant{
    private Context mContext;
    private GoodsViewHolder goodsViewHolder;
    private ArrayList<GoodsEntity> list = new ArrayList<>();
    private OnImgItemListener listener;

    public MyGoodsTypeAdapter(Context context){
        mContext = context;
    }
    //添加数据
    public void addData(List<GoodsEntity> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            goodsViewHolder = new GoodsViewHolder(
                    LayoutInflater.from(mContext).inflate(
                            R.layout.item_recycle_goods,
                            parent,false)
            );
            return goodsViewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((GoodsViewHolder) holder).tv_name.setText(list.get(position).getName());
            ((GoodsViewHolder) holder).tv_price.setText(list.get(position).getPrice() + "￥");
            ((GoodsViewHolder) holder).iv_goods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onImgItemClicked(list.get(position));
                    }
                }
            });
            ImageLoader.getInstance().displayImage(
                    ServiceConstant.IMAGE_URL + list.get(position).getPage(), ((GoodsViewHolder) holder).iv_goods, ImageLoderOptions.build_item());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(OnImgItemListener listener){
        this.listener = listener;
    }
    public interface OnImgItemListener{
        void onImgItemClicked(GoodsEntity entity);
    }
    class GoodsViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_goods;
        public TextView tv_name,tv_price;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv_goods = (ImageView) itemView.findViewById(R.id.iv_item_goods);
            tv_name = (TextView) itemView.findViewById(R.id.tv_goods_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_goods_money);
        }
    }


}
