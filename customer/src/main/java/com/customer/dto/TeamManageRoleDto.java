package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/15 0015.
 */
@ApiModel(value = "团队管理角色列表")
public class TeamManageRoleDto {

    @ApiModelProperty(value = "user表ids")
    private String uIds;

    @ApiModelProperty(value = "角色id")
    private String levelId;

    @ApiModelProperty(value = "角色名称")
    private String levelName;

    @ApiModelProperty(value = "总人数")
    private String totalNum;

    public TeamManageRoleDto() {
    }

    public TeamManageRoleDto(String levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }

    public String getuIds() {
        return uIds;
    }

    public void setuIds(String uIds) {
        this.uIds = uIds;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

}
