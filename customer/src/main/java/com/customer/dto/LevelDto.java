package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
@ApiModel(value = "等级")
public class LevelDto {

    @ApiModelProperty(value = "等级id")
    private int levelId;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

}
