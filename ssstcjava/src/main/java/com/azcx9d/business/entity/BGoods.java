package com.azcx9d.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/4 0004.
 */
public class BGoods {
    @ApiModelProperty(value = "商品id")
    private int id;

    @ApiModelProperty(value = "(商品名称)+规格名称")
    private String name;

    @ApiModelProperty(value = "商品公共id")
    private int spuId;

    @ApiModelProperty(value = "商家(店铺)id")
    private int businessId;

    @ApiModelProperty(value = "店铺名称")
    private String businessName;

    @ApiModelProperty(value = "商品分类id")
    private int categoryId;

    @ApiModelProperty(value = "品牌id（预留字段）")
    private int brandId;

    @ApiModelProperty(value = "商品价格(原价)")
    private String price;

    @ApiModelProperty(value = "市场价（促销价）")
    private String shoppingPrice;

    @ApiModelProperty(value = "点击数量")
    private int hitNum;

    @ApiModelProperty(value = "销售数量")
    private int salesNum;

    @ApiModelProperty(value = "收藏数量")
    private int collectionNum;

    @ApiModelProperty(value = "商品库存")
    private int inventoryNum;

    @ApiModelProperty(value = "商品主图")
    private String firstPic;

    @ApiModelProperty(value = "商品状态")
    private int state;

    @ApiModelProperty(value = "是否推荐")
    private int isRec;

    @ApiModelProperty(value = "详情")
    private String detail;

    @ApiModelProperty(value = "简介")
    private String synopsis;

    @ApiModelProperty(value = "时间")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    private Date lastUpdateTime;

    @ApiModelProperty(value = "是否热门（0 否， 1是）")
    private int isHot;

    @ApiModelProperty(value = "是否新品（0 否， 1是）")
    private int isNew;

    @ApiModelProperty(value = "评论id")
    private int commentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpuId() {
        return spuId;
    }

    public void setSpuId(int spuId) {
        this.spuId = spuId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(String shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    public int getHitNum() {
        return hitNum;
    }

    public void setHitNum(int hitNum) {
        this.hitNum = hitNum;
    }

    public int getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIsRec() {
        return isRec;
    }

    public void setIsRec(int isRec) {
        this.isRec = isRec;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
