package com.azcx9d.agency.entity;

import com.azcx9d.user.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 代理端服务商
 * Created by fangbaoyan on 2017/3/14.
 */
@ApiModel
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Agency extends User{
	
	private int level;
	
	@ApiModelProperty(value="等级对应中文")
	private String levelName;
	
	@ApiModelProperty(value="省市区代理中文名称")
	private List<String> agencyName;




	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	public List<String> getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(List<String> agencyName) {
		this.agencyName = agencyName;
	}
	
}
