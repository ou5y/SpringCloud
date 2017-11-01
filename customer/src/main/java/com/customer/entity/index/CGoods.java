package com.customer.entity.index;

import java.math.BigDecimal;
import java.util.Date;

public class CGoods extends CGoodsKey {
    private String name;

    private Integer spuId;

    private Integer businessId;

    private String businessName;

    private Integer categoryId;

    private Integer brandId;

    private BigDecimal price;

    private BigDecimal shoppingPrice;

    private Integer hitNum;

    private Integer salesNum;

    private Integer collectionNum;

    private Integer inventoryNum;

    private String firstPic;

    private Integer state;

    private Integer isRec;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer isHot;

    private Integer isNew;

    private Integer commentId;

    private String loopPics;

    private String showPics;

    private String articleNumber;

    private BigDecimal rangli;

    private BigDecimal expressCost;

    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(BigDecimal shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    public Integer getHitNum() {
        return hitNum;
    }

    public void setHitNum(Integer hitNum) {
        this.hitNum = hitNum;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic == null ? null : firstPic.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsRec() {
        return isRec;
    }

    public void setIsRec(Integer isRec) {
        this.isRec = isRec;
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

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getLoopPics() {
        return loopPics;
    }

    public void setLoopPics(String loopPics) {
        this.loopPics = loopPics == null ? null : loopPics.trim();
    }

    public String getShowPics() {
        return showPics;
    }

    public void setShowPics(String showPics) {
        this.showPics = showPics == null ? null : showPics.trim();
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber == null ? null : articleNumber.trim();
    }

    public BigDecimal getRangli() {
        return rangli;
    }

    public void setRangli(BigDecimal rangli) {
        this.rangli = rangli;
    }

    public BigDecimal getExpressCost() {
        return expressCost;
    }

    public void setExpressCost(BigDecimal expressCost) {
        this.expressCost = expressCost;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}