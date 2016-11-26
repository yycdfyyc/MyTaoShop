package com.yuyunchao.asus.mytaoshop.presenter.main.myself;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.event.Position;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by asus on 2016/11/24.
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {
    private String texts[] = new String[]{"个人信息", "我的商品", "上传商品"};
    private View view;
    @Override
    public TextAdapter.TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_text, parent, false);
        TextViewHolder holder = new TextViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TextAdapter.TextViewHolder holder, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Position(position));
            }
        });
        holder.tv.setText(texts[position]);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public TextViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item_text);
        }
    }
}
