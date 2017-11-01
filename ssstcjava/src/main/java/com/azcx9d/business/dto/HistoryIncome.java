package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/30 0030.
 */
@ApiModel(value = "历史收益")
public class HistoryIncome {

    @ApiModelProperty(value = "日期")
    private String days;

    @ApiModelProperty(value = "日收益")
    private String totalIncome;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
}
