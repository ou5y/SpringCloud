package com.azcx9d.agency.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商家
 * @author yfx 2017-03-22
 */
@ApiModel(value="商家")
public class Business {
	
	@ApiModelProperty(value = "商家id")
	private long id;
	
	@ApiModelProperty(value = "商家名")
	private String bName;
	
	@ApiModelProperty(value = "商家经营类型")
	private String operateType;
	
	@ApiModelProperty(value="商家经营类型对应中文")
	private String operateName;
	
	@ApiModelProperty(value = "法人姓名")
	private String legalPerson;
	
	@ApiModelProperty(value = "法人手机号")
	private String legalPersonNum;
	
	@ApiModelProperty(value = "法人身份证号码")
	private String identityCard;
	
	@ApiModelProperty(value = "上传人")
	private String uploadUser;
	
	@ApiModelProperty(value="上传人名字")
	private String uploadUserName;
	
	@ApiModelProperty(value = "商家照片")
	private String businessPhoto;
	
	@ApiModelProperty(value = "营业执照")
	private String businessLicense;
	
	@ApiModelProperty(value = "店铺地址")
	private String businessAddress;
	
	@ApiModelProperty(value = "商家状态(0:待审核,1:已审核,2:审核失败)")
	private String state;
	
	@ApiModelProperty(value = "上传时间")
	private Date uploadDate;
	
	@ApiModelProperty(value = "法人手持身份证照片")
	private String holeIdCardPhoto;
	
	@ApiModelProperty(value = "商家门头照")
	private String storePhotos;
	
	@ApiModelProperty(value = "推广员承诺书")
	private String promoterWord;
	
	@ApiModelProperty(value = "商家承诺书")
	private String businessWord;
	
	@ApiModelProperty(value = "纬度")
	private String latitude;
	
	@ApiModelProperty(value = "经度")
	private String longitude;
	
	@ApiModelProperty(value = "封顶限额")
	private BigDecimal maxAmount;
	
	@ApiModelProperty(value = "省份编码")
	private String provinceCode;
	
	@ApiModelProperty(value = "市编码")
	private String cityCode;
	
	@ApiModelProperty(value = "区域编码")
	private String areaId;
	
	@ApiModelProperty(value = "店主")
	private String userId;
	
	@ApiModelProperty(value="省市区字符串")
	private String codeString;
	
	@ApiModelProperty(value="商家手机号码")
	private String phone;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonNum() {
		return legalPersonNum;
	}

	public void setLegalPersonNum(String legalPersonNum) {
		this.legalPersonNum = legalPersonNum;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public String getBusinessPhoto() {
		return businessPhoto;
	}

	public void setBusinessPhoto(String businessPhoto) {
		this.businessPhoto = businessPhoto;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getHoleIdCardPhoto() {
		return holeIdCardPhoto;
	}

	public void setHoleIdCardPhoto(String holeIdCardPhoto) {
		this.holeIdCardPhoto = holeIdCardPhoto;
	}

	public String getStorePhotos() {
		return storePhotos;
	}

	public void setStorePhotos(String storePhotos) {
		this.storePhotos = storePhotos;
	}

	public String getPromoterWord() {
		return promoterWord;
	}

	public void setPromoterWord(String promoterWord) {
		this.promoterWord = promoterWord;
	}

	public String getBusinessWord() {
		return businessWord;
	}

	public void setBusinessWord(String businessWord) {
		this.businessWord = businessWord;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getUploadUserName() {
		return uploadUserName;
	}

	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}

	public String getCodeString() {
		return codeString;
	}

	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
