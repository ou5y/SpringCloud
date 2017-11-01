package com.azcx9d.user.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
@ApiModel
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class User {
	@ApiModelProperty(value = "用户id")
    private int id;
	@ApiModelProperty(value = "用户名称", required = false)
    private String name;
	@ApiModelProperty(value = "用户密码", required = true)
    private String password;
	@ApiModelProperty(value = "用户手机号码", required = true)
    private String phone;
	@ApiModelProperty(value = "token")
	private String token;

	@ApiModelProperty(value = "银行卡号")
	private String bankCard;
    
	@ApiModelProperty(value = "注册时间")
	private Date createTime;
	
	@ApiModelProperty(value = "善点")
	private double shandian;
	
	@ApiModelProperty(value = "身份证号码")
	private String identityCard;
	
	@ApiModelProperty(value = "交易密码")
	private String transactionPsw;
	
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	
	@ApiModelProperty(value = "身份证照片正面")
	private String identityCardUp;
	
	@ApiModelProperty(value = "身份证照片反面")
	private String identityCardDown;
	
	@ApiModelProperty(value = "推荐时间")
	private Date recoTime;
	
	@ApiModelProperty(value = "用户头像")
	private String avatar;
	
	@ApiModelProperty(value = "善心")
	private double shanxin;
	
	@ApiModelProperty(value = "积分")
	private double jifen;
	
	@ApiModelProperty(value = "被动善点")
	private double shandian2;
	
	@ApiModelProperty(value = "被动积分")
	private double jifen2;
	
	@ApiModelProperty(value = "被动善心")
	private double shanxin2;

	@ApiModelProperty(value="用户类型(0:用户，1：商家，2：服务商)")
	private int userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getShandian() {
		return shandian;
	}

	public void setShandian(double shandian) {
		this.shandian = shandian;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getTransactionPsw() {
		return transactionPsw;
	}

	public void setTransactionPsw(String transactionPsw) {
		this.transactionPsw = transactionPsw;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIdentityCardUp() {
		return identityCardUp;
	}

	public void setIdentityCardUp(String identityCardUp) {
		this.identityCardUp = identityCardUp;
	}

	public String getIdentityCardDown() {
		return identityCardDown;
	}

	public void setIdentityCardDown(String identityCardDown) {
		this.identityCardDown = identityCardDown;
	}

	public Date getRecoTime() {
		return recoTime;
	}

	public void setRecoTime(Date recoTime) {
		this.recoTime = recoTime;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public double getShanxin() {
		return shanxin;
	}

	public void setShanxin(double shanxin) {
		this.shanxin = shanxin;
	}

	public double getJifen() {
		return jifen;
	}

	public void setJifen(double jifen) {
		this.jifen = jifen;
	}

	public double getShandian2() {
		return shandian2;
	}

	public void setShandian2(double shandian2) {
		this.shandian2 = shandian2;
	}

	public double getJifen2() {
		return jifen2;
	}

	public void setJifen2(double jifen2) {
		this.jifen2 = jifen2;
	}

	public double getShanxin2() {
		return shanxin2;
	}

	public void setShanxin2(double shanxin2) {
		this.shanxin2 = shanxin2;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
}
