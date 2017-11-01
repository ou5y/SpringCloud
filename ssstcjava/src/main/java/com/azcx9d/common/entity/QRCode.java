package com.azcx9d.common.entity;

import java.util.Date;

public class QRCode {
	private int id;
	private int userId;
	private String ticket;
	private Date sqsj;//微信_二维码记录表 可通过 当前时间 与 申请时间 对比是否过期, 过期就重新申请 并入库
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Date getSqsj() {
		return sqsj;
	}
	public void setSqsj(Date sqsj) {
		this.sqsj = sqsj;
	}
	
	
}
