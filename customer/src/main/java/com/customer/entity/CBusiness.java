package com.customer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家
 * @author chenxl 2017-05-08
 */
@ApiModel("商家")
public class CBusiness {
	
	@ApiModelProperty(value = "商家id")
	private int id;
	
	@ApiModelProperty(value = "商家名")
	private String bName;
	
	@ApiModelProperty(value = "商家经营类型名称")
	private String operateName;

	@ApiModelProperty(value = "商家经营类型")
	private String operateType;
	
	@ApiModelProperty(value = "法人姓名")
	private String legalPerson;
	
	@ApiModelProperty(value = "法人手机号")
	private String legalPersonNum;
	
	@ApiModelProperty(value = "法人身份证号码")
	private String identityCard;
	
	@ApiModelProperty(value = "上传人")
	private String uploadUser;
	
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
	private String maxAmount;
	
	@ApiModelProperty(value = "省份编码")
	private String provinceCode;
	
	@ApiModelProperty(value = "市编码")
	private String cityCode;
	
	@ApiModelProperty(value = "区域编码")
	private String areaId;
	
	@ApiModelProperty(value = "店主")
	private int userId;

	@ApiModelProperty(value="商家手机号码")
	private String phone;

	@ApiModelProperty(value="省市区字符串")
	private String codeString;

	@ApiModelProperty(value="营业执照编号")
	private String businessLicenceNo;

	@ApiModelProperty(value="业务联系人")
	private String xingming;

	@ApiModelProperty(value="业务联系人手机号码")
	private String businessPhone;

	@ApiModelProperty(value = "银行卡号")
	private String bankCardNo;

	@ApiModelProperty(value = "持卡人姓名")
	private String cardHolder;

	@ApiModelProperty(value = "银行编号")
	private String bankId;

	@ApiModelProperty(value = "开户行名称")
	private String bankName;

	@ApiModelProperty(value = "卡名称")
	private String cardName;

	@ApiModelProperty(value = "卡类型")
	private String cardType;

	@ApiModelProperty(value = "纬度")
	private String latitude1;

	@ApiModelProperty(value = "经度")
	private String longitude1;

	@ApiModelProperty(value = "商家注册名称")
	private String registerName;

	@ApiModelProperty(value = "1 小额线,2 中额线 3,大额线")
	private Integer quotaType=1;

	@ApiModelProperty(value = "quotaType中文描述")
	private String quotaDesc;

	@ApiModelProperty(value = "法人身份证正面照")
	private String identityCardUp;

	@ApiModelProperty(value = "法人身份证反面照")
	private String identityCardDown;

	@ApiModelProperty(value = "分账银行卡正面照")
	private String clearanceBankCardUp;

	@ApiModelProperty(value = "开户行支行")
	private String branchName;

	@ApiModelProperty(value = "审核失败原因")
	private String failureCause;

	@ApiModelProperty(value = "让利")
	private String rangli="0";

	@ApiModelProperty(value = "经营类型表的额度类型")
	private int quotaFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCodeString() {
		return codeString;
	}

	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getBusinessLicenceNo() {
		return businessLicenceNo;
	}

	public void setBusinessLicenceNo(String businessLicenceNo) {
		this.businessLicenceNo = businessLicenceNo;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(String latitude1) {
		this.latitude1 = latitude1;
	}

	public String getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(String longitude1) {
		this.longitude1 = longitude1;
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public Integer getQuotaType() {
		return quotaType;
	}

	public void setQuotaType(Integer quotaType) {
		this.quotaType = quotaType;
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

	public String getClearanceBankCardUp() {
		return clearanceBankCardUp;
	}

	public void setClearanceBankCardUp(String clearanceBankCardUp) {
		this.clearanceBankCardUp = clearanceBankCardUp;
	}

	public String getQuotaDesc() {
		return quotaDesc;
	}

	public void setQuotaDesc(String quotaDesc) {
		this.quotaDesc = quotaDesc;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getFailureCause() {
		return failureCause;
	}

	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}

	public String getRangli() {
		return rangli;
	}

	public void setRangli(String rangli) {
		this.rangli = rangli;
	}

	public int getQuotaFlag() {
		return quotaFlag;
	}

	public void setQuotaFlag(int quotaFlag) {
		this.quotaFlag = quotaFlag;
	}

}
