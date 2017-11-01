package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/28 0028.
 */
@ApiModel("善点,积分,推荐奖励")
public class MyPointDto {

    @ApiModelProperty(value = "变动数值")
    private String value="";

    @ApiModelProperty(value = "订单金额")
    private String money="";

    @ApiModelProperty(value = "时间")
    private Date dateTime;

    @ApiModelProperty(value = "类型")
    private String type="";

    @ApiModelProperty("线额，0=默认 1=小额度 2=中额度 3=大额度")
    private int quotaType;

    @ApiModelProperty(value = "让利")
    private String rangli;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

}
