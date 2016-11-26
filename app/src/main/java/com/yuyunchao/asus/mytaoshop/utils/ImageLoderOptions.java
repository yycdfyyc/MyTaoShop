package com.yuyunchao.asus.mytaoshop.utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.yuyunchao.asus.mytaoshop.R;

/**
 * Created by asus on 2016/11/16.
 */
public class ImageLoderOptions {

    private ImageLoderOptions(){
    }
    private static DisplayImageOptions options_item;

    /**
     * 图片加载选项
     */
    public static DisplayImageOptions build_item() {
        if (options_item == null) {
            options_item = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.image_error)/*如果空url显示什么*/
                    .showImageOnLoading(R.drawable.image_loding)/*加载中显示什么*/
                    .showImageOnFail(R.drawable.image_error)/*加载失败显示什么*/
                    .cacheOnDisk(true)/*开启硬盘缓存*/
                    .cacheInMemory(true)/*开启内存缓存*/
                    .resetViewBeforeLoading(true)/*加载前重置ImageView*/
                    .build();
        }
        return options_item;
    }
}
