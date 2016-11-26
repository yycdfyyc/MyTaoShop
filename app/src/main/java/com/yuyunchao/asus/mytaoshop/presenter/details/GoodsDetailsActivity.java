package com.yuyunchao.asus.mytaoshop.presenter.details;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.entity.GoodsDetailsEntity;
import com.yuyunchao.asus.mytaoshop.network.ServiceConstant;
import com.yuyunchao.asus.mytaoshop.utils.ImageLoderOptions;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class GoodsDetailsActivity extends AppCompatActivity implements GoodsDetailsView{
    @Bind(R.id.tb_goods_details)
    public Toolbar toolbar;
    @Bind(R.id.tv_details_name)
    public TextView tv_name;
    @Bind(R.id.tv_details_price)
    public TextView tv_price;
    @Bind(R.id.tv_details_master)
    public TextView tv_master;
    @Bind(R.id.tv_details_des)
    public TextView tv_des;
    @Bind(R.id.vp_details_img)
    public ViewPager vp_img;
    @Bind(R.id.indicator)
    public CircleIndicator indicator;

    private GoodsDetailsAdapter adapter;
    private GoodsDetailsPresenter presenter;
    private String uuid;
    private static int STATE;
    private ArrayList<ImageView> imgList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.goods_details));
        presenter = new GoodsDetailsPresenter();
        presenter.onCreate();
        presenter.attachView(this);
        initIntent();
    }

    private void initIntent(){
        Intent intent = getIntent();
        STATE = intent.getIntExtra("state", 0);
        if(STATE == 1){
            uuid = intent.getStringExtra("uuid");
            presenter.getData(uuid);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTextData(GoodsDetailsEntity entity) {
        tv_name.setText(entity.getName());
        tv_price.setText("价格："+entity.getPrice()+"￥");
        tv_master.setText("发布者："+entity.getMaster());
        tv_des.setText("描述："+entity.getDescription());
    }

    @Override
    public void setImgData(ArrayList<String> imgUrl) {
        imgList.clear();
        for (int i=0;i<imgUrl.size();i++) {
            ImageView imageView = new ImageView(this);
            ImageLoader.getInstance().displayImage(
                    ServiceConstant.IMAGE_URL+imgUrl.get(i),imageView, ImageLoderOptions.build_item());
            imageView.setTag(i+"key");
            imgList.add(imageView);
            Log.i("yyc", "s" + imgList.get(i).getParent());
        }
        adapter = new GoodsDetailsAdapter(imgList);
        vp_img.setAdapter(adapter);
        indicator.setViewPager(vp_img);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter.detachView();
    }
}
