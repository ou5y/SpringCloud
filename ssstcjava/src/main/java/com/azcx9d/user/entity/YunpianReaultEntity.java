package com.azcx9d.user.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="")
public class YunpianReaultEntity {
	private Integer code;// 0代表发送成功，其他code代表出错，详细见"返回值说明"页面
	private String msg;
	private Integer count; //发送成功短信的计费条数(计费条数：70个字一条，超出70个字时按每67字一条计费)
	private Double fee;//扣费金额，单位：元，类型：双精度浮点型/double
	private long sid;//短信id，64位整型， 对应Java和C#的Long，不可用int解析
	@ApiModelProperty(value="验证码")
	private int randNum;//验证码
	private long time;
		
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public long getSid() {
		return sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public int getRandNum() {
		return randNum;
	}
	public void setRandNum(int randNum) {
		this.randNum = randNum;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
		
	
	
}
