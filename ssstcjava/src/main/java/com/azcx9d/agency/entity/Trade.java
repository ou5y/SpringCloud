package com.azcx9d.agency.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Trade {
	
	@ApiModelProperty(value = "主键id")
	private long id;
	
	@ApiModelProperty(value = "分类名称")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
