package com.azcx9d.user.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="系统消息")
public class SystemMessage {
	
	@ApiModelProperty(value="主键id,自增")
	private long id;
	
	@ApiModelProperty(value="消息类型",notes = "0:全部，1:个人")
	private int messageType;
	
	@ApiModelProperty(value="消息内容")
	private String content;
	
	@ApiModelProperty(value="是否已读")
	private int isRead;
	
	@ApiModelProperty(value="创建时间")
	private Date creatTime;
	
	@ApiModelProperty(value="用户id")
	private long userId;

	@ApiModelProperty(value="标题")
	private String title;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
