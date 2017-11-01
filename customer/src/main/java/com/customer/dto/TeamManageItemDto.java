package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/15 0015.
 */
@ApiModel(value = "团队用户item")
public class TeamManageItemDto {

    @ApiModelProperty(value = "user表id")
    private String uId;

    @ApiModelProperty(value = "角色id")
    private String levelId;

    @ApiModelProperty(value = "角色名称")
    private String levelName;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

}
