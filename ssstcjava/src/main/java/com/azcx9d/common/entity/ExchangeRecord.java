package com.azcx9d.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="兑换记录")
public class ExchangeRecord {
	
	@ApiModelProperty(value="主键id")
	private long id;
	
	@ApiModelProperty(value="用户id")
	private long userId;
	
	@ApiModelProperty(value="银行卡号")
	private String bankCardNo;
	
	@ApiModelProperty(value="兑换善点")
	private double exchangeShandian;
	
	@ApiModelProperty(value = "手续费")
	private double poundage;
	
	@ApiModelProperty(value="代扣税")
	private double tax;
	
	@ApiModelProperty(value="实际到账")
	private double realMoney;
	
	@ApiModelProperty(value="状态")
	private int state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public double getExchangeShandian() {
		return exchangeShandian;
	}

	public void setExchangeShandian(double exchangeShandian) {
		this.exchangeShandian = exchangeShandian;
	}

	public double getPoundage() {
		return poundage;
	}

	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(double realMoney) {
		this.realMoney = realMoney;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
