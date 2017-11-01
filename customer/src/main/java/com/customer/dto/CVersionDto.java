package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@ApiModel(value = "CVersionDto",description = "版本信息")
public class CVersionDto {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "版本号")
    private int versionCode;

    @ApiModelProperty(value = "版本名")
    private String versionName;

    @ApiModelProperty(value = "是否要强制更新")
    private int isUpdate;

    @ApiModelProperty(value = "更新信息")
    private String upgradeInfo;

    @ApiModelProperty(value = "更新地址")
    private String updateUrl;

    @ApiModelProperty(value = "创建时间")
    private Date creatTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getUpgradeInfo() {
        return upgradeInfo;
    }

    public void setUpgradeInfo(String upgradeInfo) {
        this.upgradeInfo = upgradeInfo;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public CVersionDto() {
    }

    public CVersionDto(int id, int versionCode, String versionName, int isUpdate, String upgradeInfo, String updateUrl, Date creatTime) {
        this.id = id;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.isUpdate = isUpdate;
        this.upgradeInfo = upgradeInfo;
        this.updateUrl = updateUrl;
        this.creatTime = creatTime;
    }
}
