package com.azcx9d.agency.entity;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Area {
	
	@ApiModelProperty(value = "区域代码",required=true)
	private String code;
	
	@ApiModelProperty(value="区域父级id，parentCode=100000表示省份",required=true)
	private String parentCode;
	
	@ApiModelProperty(value = "区域类型",required=true)
	private String type;
	
	@ApiModelProperty(value="区域名称",required=true)
	private String name;
	
	@ApiModelProperty(value="区域名称全称",required=true)
	private String fullName;
	
	@ApiModelProperty(value="区域价格")
	private BigDecimal price;
	
	@ApiModelProperty(value="代理状态0：未代理，1:已代理")
	private String state;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
