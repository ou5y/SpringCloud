package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@ApiModel(value = "积分善点")
public class CScoreChangeDto {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "变动数值")
    private String bdsz;

    @ApiModelProperty(value = "结余(变化后的数值)")
    private String jieyu;

    @ApiModelProperty(value = "变动时间")
    private Date bdsj;

    public CScoreChangeDto() {
    }

    public CScoreChangeDto(int id, String bdsz, String jieyu, Date bdsj) {
        this.id = id;
        this.bdsz = bdsz;
        this.jieyu = jieyu;
        this.bdsj = bdsj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBdsz() {
        return bdsz;
    }

    public void setBdsz(String bdsz) {
        this.bdsz = bdsz;
    }

    public String getJieyu() {
        return jieyu;
    }

    public void setJieyu(String jieyu) {
        this.jieyu = jieyu;
    }

    public Date getBdsj() {
        return bdsj;
    }

    public void setBdsj(Date bdsj) {
        this.bdsj = bdsj;
    }
}
