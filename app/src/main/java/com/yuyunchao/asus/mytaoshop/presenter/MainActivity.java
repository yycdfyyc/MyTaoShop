package com.yuyunchao.asus.mytaoshop.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.event.Login;
import com.yuyunchao.asus.mytaoshop.mvpbase.MvpView;
import com.yuyunchao.asus.mytaoshop.presenter.main.chat.ChatFragment;
import com.yuyunchao.asus.mytaoshop.presenter.main.list.MailListFragment;
import com.yuyunchao.asus.mytaoshop.presenter.main.myself.MyselfFragment;
import com.yuyunchao.asus.mytaoshop.presenter.main.shop.GoodsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements MvpView {
    @Bind(R.id.tv_title)
    public TextView tv_title;

    @Bind({R.id.tv_shop, R.id.tv_message,R.id.tv_mail_list,R.id.tv_me})
    TextView[] tv;

    @Bind(R.id.vp_pagers)
    public ViewPager viewPager;

    private FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new GoodsFragment();
                case 1:
                    return new ChatFragment();
                case 2:
                    return new MailListFragment();
                case 3:
                    return new MyselfFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }
    //当内容改变时
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI(){
        tv[0].setSelected(true);
        tv[0].setTextColor(getResources().getColor(R.color.colorPrimary));
        //viewPager监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //初始化linear中的数据
                for (int i = 0; i < tv.length; i++) {
                    tv[i].setSelected(false);
                    tv[i].setTextColor(getResources().getColor(R.color.gray));
                }
                tv_title.setText(tv[position].getText());
                tv[position].setSelected(true);
                tv[position].setTextColor(getResources().getColor(R.color.colorPrimary));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(pagerAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_shop, R.id.tv_message,R.id.tv_mail_list,R.id.tv_me})
    public void onClick(View view){
        for (int i = 0;i<tv.length;i++) {
            tv[i].setSelected(false);
            tv[i].setTextColor(getResources().getColor(R.color.gray));
            tv[i].setTag(i);
        }
        view.setSelected(true);
        //切换viewpager的当前页，并设置为瞬间切换(false)
        viewPager.setCurrentItem((Integer) view.getTag(),false);
        ((TextView)view).setTextColor(getResources().getColor(R.color.colorPrimary));
        tv_title.setText(tv[(Integer) view.getTag()].getText());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void login(Login login){
        if (login.islogin()) {
            viewPager.setCurrentItem(0);
            viewPager.setAdapter(pagerAdapter);
        }
    }



}
