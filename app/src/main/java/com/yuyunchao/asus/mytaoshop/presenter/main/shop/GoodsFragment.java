package com.yuyunchao.asus.mytaoshop.presenter.main.shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.entity.GoodsEntity;
import com.yuyunchao.asus.mytaoshop.presenter.details.GoodsDetailsActivity;
import com.yuyunchao.asus.mytaoshop.presenter.main.shop.goodstype.GoodsTypeActivity;
import com.yuyunchao.asus.mytaoshop.utils.myview.MyLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsFragment extends Fragment implements GoodsView,
        GoodsTypeAdapter.OnItemClickListener,
        GoodsRecycleAdapter.OnImgItemListener{

    @Bind(R.id.rv_my_goods)
    public RecyclerView rv_goods;
    @Bind(R.id.pcf_flash)
    public MyLayout ptrLayout;
    @Bind(R.id.im_back_top)
    public ImageView im_back_top;
    private String[] types = new String[]{"","household","electron","dress","toy","book","gift","other"};
    private int nowType;
    private View v;
    private int[] ds;
    private ArrayList<GoodsEntity> list = new ArrayList<>();
    private ArrayList<ImageView> imgList = new ArrayList<>();
    private GoodsRecycleAdapter adapter;
    public static GoodsTypeAdapter typeAdapter;
    private GoodsPresenter goodsPresenter;
    public GoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImgViewData();
        adapter = new GoodsRecycleAdapter(getActivity(),imgList);
        goodsPresenter = new GoodsPresenter();
        goodsPresenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_goods, container, false);
        ButterKnife.bind(this,v);
        goodsPresenter.attachView(this);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycle();
        rv_goods.setAdapter(adapter);
    }
    private void initImgViewData(){
        ds = new int[]{R.drawable.rec1,R.drawable.rec2,R.drawable.rec3,R.drawable.rec4,R.drawable.rec5};
        for (int i = 0; i < ds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(ds[i]);
            imageView.setTag("yycImgREC"+i);
            imgList.add(imageView);
        }
    }
    private GridLayoutManager.SpanSizeLookup mySpan = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            if (position==0||position==1)
            return 2;
            else
            return 1;
        }
    };
    private void initRecycle(){
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(mySpan);
        rv_goods.setLayoutManager(manager);
        adapter.setListener(this);
//        rv_goods_type.setLayoutManager(new GridLayoutManager(getActivity(),4));
        typeAdapter= new GoodsTypeAdapter();
        typeAdapter.setListener(this);
//        rv_goods_type.setAdapter(typeAdapter);
        rv_goods.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager manager1 = (LinearLayoutManager) layoutManager;
                    if (manager1.findFirstVisibleItemPosition() > 1) {
                        im_back_top.setVisibility(View.VISIBLE);
                    }else {
                        im_back_top.setVisibility(View.GONE);
                    }
                }
            }
        });
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setDurationToCloseHeader(1000);
        ptrLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                //加载更多
                goodsPresenter.getLoadMoreData(types[nowType]);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //下拉刷新
                goodsPresenter.getRefalshData(1,types[nowType]);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        /*当前页面没有数据时,刷新*/
        if (adapter.getItemCount() <= 2) {
            ptrLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ptrLayout.autoRefresh();
                }
            }, 200);
        }
    }
    @OnClick(R.id.im_back_top)
    public void onclick(View v){
        rv_goods.scrollToPosition(0);
        im_back_top.setVisibility(View.GONE);
    }
    @Override
    public void onDestroyView() {
        super.onDestroy();
        goodsPresenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        goodsPresenter.onDestroy();
    }

    @Override
    public void hideRefresh() {
        ptrLayout.refreshComplete();
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

    /**
     * 分类点击事件的监听实现
     */
    @Override
    public void onItemClickListener(int position) {
        if (nowType != position) {
            typeAdapter.setNowPosition(position);
            nowType = position;
            goodsPresenter.getRefalshData(1,types[nowType]);
            Intent intent = new Intent(getActivity(), GoodsTypeActivity.class);
            intent.putExtra("type", types[nowType]);
            startActivity(intent);
        }


    }

    @Override
    public void onImgItemClicked(GoodsEntity entity) {
        Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
//        Toast.makeText(getActivity(), "" + entity.getUuid(), Toast.LENGTH_SHORT).show();
        intent.putExtra("uuid", entity.getUuid());
        intent.putExtra("state", 1);
        startActivity(intent);
    }


}
