package com.yuyunchao.asus.mytaoshop.model.entity;

/**
 * Created by asus on 2016/11/16.
 */
public class GoodsEntity {
    //图片url
    private String page;
    //商品名称
    private String name;
    //商品价格
    private String price;
    //商品描述
    private String description;
    //商品类型
    private String type;
    //在商品表中的主键
    private String uuid;
    //商品发布者
    private String master;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
