package com.yuyunchao.asus.mytaoshop.presenter.main.chat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.utils.CacheUserPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (CacheUserPreferences.isLogin){
            return inflater.inflate(R.layout.fragment_chat_login, container, false);
        }else {
            return inflater.inflate(R.layout.fragment_chat, container, false);
        }
    }

}
