package com.azcx9d.agency.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 银行卡
 * @author yfx 2017-03-25 10:30:00
 *
 */
@ApiModel
public class BankCard {
	
	@ApiModelProperty(value = "主键，自增")
	private long id;
	
	@ApiModelProperty(value = "银行卡号")
	private String bankCardNo;
	
	@ApiModelProperty(value = "开户行名称")
	private String bankName;
	
	@ApiModelProperty(value = "银行卡持有人id")
	private long userId;
	
	@ApiModelProperty(value="持卡人名称")
	private String cardHolder;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

}
