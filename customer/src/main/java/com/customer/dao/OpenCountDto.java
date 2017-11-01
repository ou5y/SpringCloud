package com.customer.dao;

import com.customer.dto.LevelDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@ApiModel(value = "开通数量统计")
public class OpenCountDto {

    @ApiModelProperty(value = "剩余名额")
    private int remainingQuota;

    @ApiModelProperty(value = "总名额数量")
    private int openLimit;

    @ApiModelProperty(value = "是否可开通")
    private boolean isOpenable;

    @ApiModelProperty(value = "等级列表")
    private List<LevelDto> roleList;

    public int getRemainingQuota() {
        return remainingQuota;
    }

    public void setRemainingQuota(int remainingQuota) {
        this.remainingQuota = remainingQuota;
    }

    public int getOpenLimit() {
        return openLimit;
    }

    public void setOpenLimit(int openLimit) {
        this.openLimit = openLimit;
    }

    public boolean getIsOpenable() {
        return isOpenable;
    }

    public void setIsOpenable(boolean isOpenable) {
        isOpenable = isOpenable;
    }

    public List<LevelDto> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<LevelDto> roleList) {
        this.roleList = roleList;
    }

}
