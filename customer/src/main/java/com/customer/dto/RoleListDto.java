package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@ApiModel(value = "角色列表")
public class RoleListDto {

    @ApiModelProperty(value = "角色id")
    private Integer levelId;

    @ApiModelProperty(value = "角色名称")
    private String levelName;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

}
