package com.azcx9d.mybatisgenerator.model;

import java.math.BigDecimal;

public class BusinessSupportInfo extends BusinessSupportInfoKey {
    private Integer bId;

    private String companyName;

    private String bankCode;

    private String bankName;

    private String bankBranch;

    private String bankAccountNo;

    private String bankAccountName;

    private Integer location;

    private String certName;

    private String certType;

    private String certPhone;

    private String certId;

    private String bizLinkMan;

    private String bizPhone;

    private BigDecimal startVal;

    private BigDecimal endVal;

    private String feeType;

    private String feeValue;

    private String minValue;

    private String maxValue;

    private Integer refundFeeruleFlag;

    private String settleTdflag;

    private Integer settleCycle;

    private BigDecimal minTransferAmt;

    private String payType;

    private String merchDiv;

    private String merchantName;

    private String licenceNo;

    private Integer state;

    private String failurecause;

    private String merchantNumber;

    private Integer merchantStatus;

    private String body;

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch == null ? null : bankBranch.trim();
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo == null ? null : bankAccountNo.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName == null ? null : certName.trim();
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getCertPhone() {
        return certPhone;
    }

    public void setCertPhone(String certPhone) {
        this.certPhone = certPhone == null ? null : certPhone.trim();
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId == null ? null : certId.trim();
    }

    public String getBizLinkMan() {
        return bizLinkMan;
    }

    public void setBizLinkMan(String bizLinkMan) {
        this.bizLinkMan = bizLinkMan == null ? null : bizLinkMan.trim();
    }

    public String getBizPhone() {
        return bizPhone;
    }

    public void setBizPhone(String bizPhone) {
        this.bizPhone = bizPhone == null ? null : bizPhone.trim();
    }

    public BigDecimal getStartVal() {
        return startVal;
    }

    public void setStartVal(BigDecimal startVal) {
        this.startVal = startVal;
    }

    public BigDecimal getEndVal() {
        return endVal;
    }

    public void setEndVal(BigDecimal endVal) {
        this.endVal = endVal;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    public String getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(String feeValue) {
        this.feeValue = feeValue == null ? null : feeValue.trim();
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue == null ? null : minValue.trim();
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue == null ? null : maxValue.trim();
    }

    public Integer getRefundFeeruleFlag() {
        return refundFeeruleFlag;
    }

    public void setRefundFeeruleFlag(Integer refundFeeruleFlag) {
        this.refundFeeruleFlag = refundFeeruleFlag;
    }

    public String getSettleTdflag() {
        return settleTdflag;
    }

    public void setSettleTdflag(String settleTdflag) {
        this.settleTdflag = settleTdflag == null ? null : settleTdflag.trim();
    }

    public Integer getSettleCycle() {
        return settleCycle;
    }

    public void setSettleCycle(Integer settleCycle) {
        this.settleCycle = settleCycle;
    }

    public BigDecimal getMinTransferAmt() {
        return minTransferAmt;
    }

    public void setMinTransferAmt(BigDecimal minTransferAmt) {
        this.minTransferAmt = minTransferAmt;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getMerchDiv() {
        return merchDiv;
    }

    public void setMerchDiv(String merchDiv) {
        this.merchDiv = merchDiv == null ? null : merchDiv.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo == null ? null : licenceNo.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFailurecause() {
        return failurecause;
    }

    public void setFailurecause(String failurecause) {
        this.failurecause = failurecause == null ? null : failurecause.trim();
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber == null ? null : merchantNumber.trim();
    }

    public Integer getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(Integer merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }
}