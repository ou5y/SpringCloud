package com.customer.dto.my;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class RecommendLogDto {

    @ApiModelProperty(value = "Id")
    private Integer id;

    @ApiModelProperty(value = "推荐人号码")
    private String recommendPhone;

    @ApiModelProperty(value = "被推荐人号码")
    private String recommendedPhone;

//    @ApiModelProperty(value = "提交时间")
//    private Date createTime;

    @ApiModelProperty(value = "等级Id")
    private Integer levelId;

    @ApiModelProperty(value = "身份")
    private String levelName;

    @ApiModelProperty(value = "被推荐人头像")
    private String avatar;

    @ApiModelProperty(value = "是否选中")
    private boolean isSelected =false;

    @ApiModelProperty(value = "identityId")
    private Integer identityId;

    @ApiModelProperty(value = "状态： 0：待审核  1：审核通过  2：审核失败")
    private int state;

    public String getRecommendPhone() {
        return recommendPhone;
    }

    public void setRecommendPhone(String recommendPhone) {
        this.recommendPhone = recommendPhone;
    }

    public String getRecommendedPhone() {
        return recommendedPhone;
    }

    public void setRecommendedPhone(String recommendedPhone) {
        this.recommendedPhone = recommendedPhone;
    }

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean selected) {
        isSelected = selected;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
