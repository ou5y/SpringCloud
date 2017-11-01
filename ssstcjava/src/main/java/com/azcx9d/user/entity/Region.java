package com.azcx9d.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="区域")
public class Region {
	
	@ApiModelProperty(value="编码")
	private int code;
	
	@ApiModelProperty(value="上级编码")
	private int parentCode;
	
	@ApiModelProperty(value="类型，0:国家  1：省   2：市   3：区")
	private int type;
	
	@ApiModelProperty(value="名称")
	private String name;
	
	@ApiModelProperty(value="全名")
	private String fullName;
	
	@ApiModelProperty(value="代理价格")
	private float price;
	
	@ApiModelProperty(value="状态，0：未被代理，1：已被代理")
	private int state;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getParentCode() {
		return parentCode;
	}

	public void setParentCode(int parentCode) {
		this.parentCode = parentCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
