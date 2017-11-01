package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/27 0027.
 */
@ApiModel("商品详情")
public class GoodsDto {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品主图")
    private String firstPic;

    @ApiModelProperty(value = "轮播图片(3张)")
    private String loopPics;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品分类描述")
    private String categoryDesc;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "折扣价")
    private String shoppingPrice;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "展示图(9张)")
    private String showPics;

    @ApiModelProperty(value = "商品描述")
    private String detail;

    @ApiModelProperty(value = "商品货号")
    private String articleNumber;

    @ApiModelProperty(value = "商品库存")
    private Integer inventoryNum;

    @ApiModelProperty(value = "商品让利")
    private String rangli;

    @ApiModelProperty(value = "快递费用")
    private String expressCost;

    @ApiModelProperty(value = "商品分类父id")
    private Integer categoryParentId;

    @ApiModelProperty(value = "商品类型")
    private GoodsCategoryDto goodsCategoryDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public String getLoopPics() {
        return loopPics;
    }

    public void setLoopPics(String loopPics) {
        this.loopPics = loopPics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getShowPics() {
        return showPics;
    }

    public void setShowPics(String showPics) {
        this.showPics = showPics;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

    public String getExpressCost() {
        return expressCost;
    }

    public void setExpressCost(String expressCost) {
        this.expressCost = expressCost;
    }

    public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public GoodsCategoryDto getGoodsCategoryDto() {
        return goodsCategoryDto;
    }

    public void setGoodsCategoryDto(GoodsCategoryDto goodsCategoryDto) {
        this.goodsCategoryDto = goodsCategoryDto;
    }

}
