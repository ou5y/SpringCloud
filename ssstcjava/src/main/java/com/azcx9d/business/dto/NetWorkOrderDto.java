package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/28 0028.
 */
@ApiModel("网络订单")
public class NetWorkOrderDto {

    @ApiModelProperty(value = "订单id")
    private Integer id;

    @ApiModelProperty(value = "订单金额")
    private String money;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "让利(让利金额=money*rangli)")
    private String rangli;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "订单状态(-1:商家关闭, -2:审核失败)")
    private Integer state;

    @ApiModelProperty(value = "让利金额")
    private String rangliMoney;

    @ApiModelProperty(value = "订单状态描述")
    private String stateDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRangliMoney() {
        return rangliMoney;
    }

    public void setRangliMoney(String rangliMoney) {
        this.rangliMoney = rangliMoney;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }
}
