package com.yuyunchao.asus.mytaoshop.presenter.main.shop.goodstype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.entity.GoodsEntity;
import com.yuyunchao.asus.mytaoshop.presenter.details.GoodsDetailsActivity;
import com.yuyunchao.asus.mytaoshop.presenter.main.shop.GoodsPresenter;
import com.yuyunchao.asus.mytaoshop.presenter.main.shop.GoodsRecycleAdapter;
import com.yuyunchao.asus.mytaoshop.presenter.main.shop.GoodsView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class GoodsTypeActivity extends AppCompatActivity implements GoodsView,MyGoodsTypeAdapter.OnImgItemListener {
    @Bind(R.id.tv_type_title)
    TextView tv_title;
    @Bind(R.id.tb_goods_type)
    Toolbar tb_type;
    @Bind(R.id.ptr_type)
    PtrFrameLayout ptr;
    @Bind(R.id.rv_type)
    RecyclerView rv_type;
    private String type;
    private ArrayList<GoodsEntity> list;
    private GoodsPresenter presenter;
    private MyGoodsTypeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_type);
        ButterKnife.bind(this);
        getMyIntent();
        setSupportActionBar(tb_type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        adapter = new MyGoodsTypeAdapter(this);
        presenter = new GoodsPresenter();
        presenter.attachView(this);
        adapter.setListener(this);
        initUI();
        rv_type.setAdapter(adapter);
    }

    private void initUI(){
        rv_type.setLayoutManager(new GridLayoutManager(this,2));
//        ptr.setLastUpdateTimeRelateObject(this);
        ptr.setDurationToCloseHeader(1000);
        ptr.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                presenter.getLoadMoreData(type);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getRefalshData(1,type);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter.getItemCount() == 0) {
            ptr.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ptr.autoRefresh();
                }
            }, 200);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
    private void getMyIntent(){
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        switch (type) {
            case "":
                tv_title.setText(getResources().getString(R.string.all));
                break;
            case "household":
                tv_title.setText(getResources().getString(R.string.household));
                break;
            case "electron":
                tv_title.setText(getResources().getString(R.string.electron));
                break;
            case "toy":
                tv_title.setText(getResources().getString(R.string.toy));
                break;
            case "gift":
                tv_title.setText(getResources().getString(R.string.gift));
                break;
            case "dress":
                tv_title.setText(getResources().getString(R.string.dress));
                break;
            case "book":
                tv_title.setText(getResources().getString(R.string.book));
                break;
            case "other":
                tv_title.setText(getResources().getString(R.string.other));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void hideRefresh() {
        ptr.refreshComplete();
    }

    @Override
    public void addRefreshData(ArrayList<GoodsEntity> data) {
        adapter.clear();
        if (data != null) {
            adapter.addData(data);
        }

    }

    @Override
    public void loadMoreData(ArrayList<GoodsEntity> data) {
        if (data != null) {
            adapter.addData(data);
        }
    }

    @Override
    public void loadDataError() {

    }

    @Override
    public void onImgItemClicked(GoodsEntity entity) {
        Intent intent = new Intent(this, GoodsDetailsActivity.class);
//        Toast.makeText(getActivity(), "" + entity.getUuid(), Toast.LENGTH_SHORT).show();
        intent.putExtra("uuid", entity.getUuid());
        intent.putExtra("state", 1);
        startActivity(intent);
    }
}
