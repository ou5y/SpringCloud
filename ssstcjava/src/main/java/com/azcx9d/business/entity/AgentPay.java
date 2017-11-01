package com.azcx9d.business.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yangpn on 2017-08-22 17:41
 */
public class AgentPay {
    private int id;
    private int orderId;
    private int sellerId;
    private BigDecimal royaltyMoney;
    private Date createime;
    private BigDecimal orderMoney;
    private BigDecimal rangli;
    private String bankCardo;
    private String cardHolder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public BigDecimal getRoyaltyMoney() {
        return royaltyMoney;
    }

    public void setRoyaltyMoney(BigDecimal royaltyMoney) {
        this.royaltyMoney = royaltyMoney;
    }

    public Date getCreateime() {
        return createime;
    }

    public void setCreateime(Date createime) {
        this.createime = createime;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getRangli() {
        return rangli;
    }

    public void setRangli(BigDecimal rangli) {
        this.rangli = rangli;
    }

    public String getBankCardo() {
        return bankCardo;
    }

    public void setBankCardo(String bankCardo) {
        this.bankCardo = bankCardo;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }
}
