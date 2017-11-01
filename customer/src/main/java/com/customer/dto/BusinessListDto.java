package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@ApiModel(value = "商户列表")
public class BusinessListDto {

    @ApiModelProperty(value = "主键id")
    private long id;

    @ApiModelProperty(value = "商户名称")
    private String bName;

    @ApiModelProperty(value = "商家照片")
    private String businessPhoto;

    @ApiModelProperty(value = "法人姓名")
    private String legalPerson;

    @ApiModelProperty(value = "法人手机号码")
    private String legalPersonNum;

    @ApiModelProperty(value = "状态")
    private int state;

    @ApiModelProperty(value = "上传时间")
    private Date uploadDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getBusinessPhoto() {
        return businessPhoto;
    }

    public void setBusinessPhoto(String businessPhoto) {
        this.businessPhoto = businessPhoto;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonNum() {
        return legalPersonNum;
    }

    public void setLegalPersonNum(String legalPersonNum) {
        this.legalPersonNum = legalPersonNum;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
