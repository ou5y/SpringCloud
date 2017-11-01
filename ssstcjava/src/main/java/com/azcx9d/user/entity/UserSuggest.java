package com.azcx9d.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户建议反馈")
public class UserSuggest {
	
	@ApiModelProperty(value="主键id")
	public long id;
	
	@ApiModelProperty(value="建议内容")
	public String content;
	
	@ApiModelProperty(value="用户id")
	public long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
