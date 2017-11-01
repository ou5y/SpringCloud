package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by fangbaoyan on 2017/5/17.
 */
@ApiModel(value = "推荐奖励记录")
public class MyRecommendPointRecordDto {

    @ApiModelProperty(value = "奖励方式")
    private String type;

    @ApiModelProperty(value = "时间")
    private Date dateTime;

    @ApiModelProperty(value = "奖励值")
    private String value;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
