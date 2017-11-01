package com.azcx9d.agency.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AgencyApplyRecord {
	private int id;
	
	@ApiModelProperty(value="联系人",required=true)
	private String linkmanName;
	
	@ApiModelProperty(value="联系电话",required=true)
	private String linkPhone;
	
	@ApiModelProperty(value="代理区域id",required=true)
	private int areaId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	
}
