package com.azcx9d.agency.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="消费者对账表")
public class CustomBill {
	
	@ApiModelProperty(value="主键id")
	private long id;
	
	@ApiModelProperty(value="类型")
	private int leixing;
	
	@ApiModelProperty(value="用户id")
	private long userId;
	
	@ApiModelProperty(value="变动数值")
	private double bdsz;
	
	@ApiModelProperty(value="结余")
	private double jieyu;
	
	@ApiModelProperty(value="变动时间")
	private Date bdsj;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLeixing() {
		return leixing;
	}

	public void setLeixing(int leixing) {
		this.leixing = leixing;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getBdsz() {
		return bdsz;
	}

	public void setBdsz(double bdsz) {
		this.bdsz = bdsz;
	}

	public double getJieyu() {
		return jieyu;
	}

	public void setJieyu(double jieyu) {
		this.jieyu = jieyu;
	}

	public Date getBdsj() {
		return bdsj;
	}

	public void setBdsj(Date bdsj) {
		this.bdsj = bdsj;
	}
	
}
