package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@ApiModel("订单信息")
public class COrderListDto {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "商户id")
    private int storeId;

    @ApiModelProperty(value = "消费金额")
    private String money;

    @ApiModelProperty(value = "订单状态(-1:商家关闭,-2:审核失败)")
    private int state;

    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;

    @ApiModelProperty(value = "奖励积分")
    private String jifen;

    @ApiModelProperty(value = "额线(0:综合, 1:小额线, 2:中额线, 3:大额线)")
    private int quotaType;

    @ApiModelProperty(value = "额线描述")
    private String quotaDesc;

    @ApiModelProperty(value = "奖励")
    private String jiangli;

    @ApiModelProperty(value = "订单状态描述")
    private String stateDesc;

    @ApiModelProperty(value = "抵用描述")
    private String offsetDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

    public String getQuotaDesc() {
        return quotaDesc;
    }

    public void setQuotaDesc(String quotaDesc) {
        this.quotaDesc = quotaDesc;
    }

    public String getJiangli() {
        return jiangli;
    }

    public void setJiangli(String jiangli) {
        this.jiangli = jiangli;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public String getOffsetDesc() {
        return offsetDesc;
    }

    public void setOffsetDesc(String offsetDesc) {
        this.offsetDesc = offsetDesc;
    }

}
