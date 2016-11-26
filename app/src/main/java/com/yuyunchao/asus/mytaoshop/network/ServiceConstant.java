package com.yuyunchao.asus.mytaoshop.network;

/**
 * Created by asus on 2016/11/16.
 */
public interface ServiceConstant {
    /*易淘总路径*/
    String BASE_URL = "http://wx.feicuiedu.com:9094/yitao/";
    /*图片的基路径*/
    String IMAGE_URL = "http://wx.feicuiedu.com:9094";

    /*商品模块根路径*/
    String GOODS_BASE_URL = "GoodsServlet?method=";
    //获取所有商品
    String GOODS_ALL_URL = "getAll" ;
    //添加商品
    String GOODS_ADD_URL = "add" ;
    //删除商品
    String GOODS_DELETE_URL = "delete" ;
    //商品详情
    String GOODS_VIEW_URL = "view" ;

    /*用户模块根路径*/
    String USER_BASE_URL = "UserWeb?method=";
    //注册
    String REGISTER_URL = "register";
    //登录
    String LOGIN_URL = "login";
    //更新
    String UPDATE_URL = "update";
    //获取好友列表(根据环信ID数组获取用户信息)
    String GETNAME_URL = "getNames";
    //查找好友(根据昵称获取用户信息)
    String GETUSER_URL = "getUsers";

}
