package com.customer.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CCollect {

    private Integer id;

    private String name;

    private Integer type;

    private String price;

    private String imgUrl;

    private Date createTime;

    private Integer gbId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getGbId() {
        return gbId;
    }

    public void setGbId(Integer gbId) {
        this.gbId = gbId;
    }
}