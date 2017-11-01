package com.azcx9d.agency.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "兑换记录")
public class Exchange {
	
	@ApiModelProperty(value="主键id")
	private long id;
	
	@ApiModelProperty(value="用户id")
	private long userId;
	
	@ApiModelProperty(value="银行卡号")
	private String bankCardNo;
	
	@ApiModelProperty(value="兑换善点")
	private double exchangeShandian;
	
	@ApiModelProperty(value="手续费")
	private double poundage;
	
	@ApiModelProperty(value="代扣税")
	private double tax;
	
	@ApiModelProperty(value="实际到账金额")
	private double realMoney;
	
	@ApiModelProperty(value="状态，0:待打款，1:打款成功")
	private int state;
	
	@ApiModelProperty(value="创建时间")
	private Date creatDate;
	
	@ApiModelProperty(value="到账方式，1:T+1、3:T+3、7:T+7")
	private int arrivalMode;
	
	@ApiModelProperty(value="确认时间")
	private Date confirmDate;
	
	@ApiModelProperty(value="结余")
	private double balance;
	
	@ApiModelProperty(value="用户名")
	private String name;
	
	@ApiModelProperty(value="开户行名称")
	private String bankName;
	
	@ApiModelProperty(value="银行卡持有人名称")
	private String cardHolder;

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

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public int getArrivalMode() {
		return arrivalMode;
	}

	public void setArrivalMode(int arrivalMode) {
		this.arrivalMode = arrivalMode;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

}
