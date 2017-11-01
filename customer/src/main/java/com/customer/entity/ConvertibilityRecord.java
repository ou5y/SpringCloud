package com.customer.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ConvertibilityRecord {
    private Integer id;

    private Integer userId;

    private Integer cShandian;

    private Double jieyu;

    private BigDecimal poundage;

    private Integer arrivalMode;

    private BigDecimal sjdk;

    private Integer state;

    private Date createDate;

    private Date qrsj;

    private Integer bankId;

    private String bankcardNo;

    private String failurecause;

    private Integer type;

    private String bankCardNo;

    private String bankName;

    private String cardHolder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getcShandian() {
        return cShandian;
    }

    public void setcShandian(Integer cShandian) {
        this.cShandian = cShandian;
    }

    public Double getJieyu() {
        return jieyu;
    }

    public void setJieyu(Double jieyu) {
        this.jieyu = jieyu;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public Integer getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(Integer arrivalMode) {
        this.arrivalMode = arrivalMode;
    }

    public BigDecimal getSjdk() {
        return sjdk;
    }

    public void setSjdk(BigDecimal sjdk) {
        this.sjdk = sjdk;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getQrsj() {
        return qrsj;
    }

    public void setQrsj(Date qrsj) {
        this.qrsj = qrsj;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankcardNo() {
        return bankcardNo;
    }

    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo == null ? null : bankcardNo.trim();
    }

    public String getFailurecause() {
        return failurecause;
    }

    public void setFailurecause(String failurecause) {
        this.failurecause = failurecause == null ? null : failurecause.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder == null ? null : cardHolder.trim();
    }
}