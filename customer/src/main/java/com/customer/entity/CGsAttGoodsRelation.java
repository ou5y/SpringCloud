package com.customer.entity;

public class CGsAttGoodsRelation {
    private Integer id;

    private Integer goodsId;

    private Integer attOptionId;

    private String attName;

    private String attOption;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAttOptionId() {
        return attOptionId;
    }

    public void setAttOptionId(Integer attOptionId) {
        this.attOptionId = attOptionId;
    }

    public String getAttName() {
        return attName;
    }

    public void setAttName(String attName) {
        this.attName = attName == null ? null : attName.trim();
    }

    public String getAttOption() {
        return attOption;
    }

    public void setAttOption(String attOption) {
        this.attOption = attOption == null ? null : attOption.trim();
    }
}