package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/17 0017.
 */
@ApiModel(value = "总收益")
public class IncomeTotalDto {

    @ApiModelProperty(value = "级别id")
    private int levelId;

    @ApiModelProperty(value = "级别名称")
    private String levelName;

    @ApiModelProperty(value = "总人数")
    private int totalPerson;

    @ApiModelProperty(value = "总收益")
    private String totalIncome;

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

    public int getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(int totalPerson) {
        this.totalPerson = totalPerson;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
}
