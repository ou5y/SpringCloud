package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@ApiModel(value = "开通记录")
public class OpenLogDto {

    @ApiModelProperty(value = "推荐人手机号码")
    private String recommendPhone;

    @ApiModelProperty(value = "被推荐人手机号码")
    private String recommendedPhone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
