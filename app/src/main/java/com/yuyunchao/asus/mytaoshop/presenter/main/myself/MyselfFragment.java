package com.yuyunchao.asus.mytaoshop.presenter.main.myself;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.pkmmte.view.CircularImageView;
import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.event.Position;
import com.yuyunchao.asus.mytaoshop.network.ServiceConstant;
import com.yuyunchao.asus.mytaoshop.presenter.main.myself.user.login.LoginActivity;
import com.yuyunchao.asus.mytaoshop.utils.CacheUserPreferences;
import com.yuyunchao.asus.mytaoshop.utils.ImageLoderOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyselfFragment extends Fragment {
    @Bind(R.id.rv_text)
    RecyclerView rv_text;
    @Bind(R.id.tv_nick_name)
    TextView tv_name;
    @Bind(R.id.im_head)
    CircularImageView im_head;
    public MyselfFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myself, container, false);
        ButterKnife.bind(this,view);
        rv_text.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_text.setAdapter(new TextAdapter());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(CacheUserPreferences.isLogin){
            CacheUserPreferences.init(getActivity());
            if (CacheUserPreferences.getUser().getNickname()==null||CacheUserPreferences.getUser().getNickname().equals(""))
            tv_name.setText(CacheUserPreferences.getUser().getUsername());
            else tv_name.setText(CacheUserPreferences.getUser().getNickname());
            ImageLoader.getInstance().displayImage(ServiceConstant.IMAGE_URL+CacheUserPreferences.getUser().getOther(),
                    im_head, ImageLoderOptions.build_item());
        }
    }

    @OnClick({R.id.im_head,R.id.tv_nick_name})
    public void click(View view){
       onEventBus(new Position(0));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Position position) {
        CacheUserPreferences.init(getActivity());
        if (!CacheUserPreferences.isLogin){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }else {
            switch (position.getPosition()) {
                case 0:
                    Toast.makeText(getActivity(), "进入个人信息界面", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getActivity(), "进入商品界面", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getActivity(), "进入上传商品界面", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }








    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
