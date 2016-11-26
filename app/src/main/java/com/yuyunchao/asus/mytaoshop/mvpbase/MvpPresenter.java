package com.yuyunchao.asus.mytaoshop.mvpbase;

import android.support.annotation.NonNull;

/**
 * Created by asus on 2016/11/14.
 */
public abstract class MvpPresenter<V extends MvpView> {
    //实现mvpView的具体类（activity,fragment...）的对象
    private V view;

    /**
     * Presenter创建的回调。
     * <p/>
     * 在Activity或Fragment的onCreate()方法中调用。
     */
    public final void onCreate() {
//        EventBus.getDefault().register(this);
    }
    /**
     * Presenter销毁的回调。
     * <p/>
     * 在Activity或Fragment的onDestroy()方法中调用。
     */

    public final void onDestroy() {
//        EventBus.getDefault().unregister(this);
    }
    /**
     * Presenter和视图关联。
     * <p/>
     * 在Activity的onCreate()中调用。
     * <p/>
     * 在Fragment的onViewCreated()或onActivityCreated()方法中调用。
     */
    public final void attachView(V view) {
        this.view = view;
    }

    /**
     * Presenter和视图解除关联。
     * <p/>
     * 在Activity的onDestroy()中调用。
     * <p/>
     * 在Fragment的onViewDestroyed()中调用。
     */
    public void detachView() {
        // 使用Null Object Pattern，避免子类使用getView()方法时频繁检查null值。
        this.view = getNullObject();
    }

    protected final V getView() {
        return view;
    }

    protected abstract @NonNull V getNullObject();
}
