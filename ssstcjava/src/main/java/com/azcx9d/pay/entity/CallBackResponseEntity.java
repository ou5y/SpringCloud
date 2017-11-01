package com.azcx9d.pay.entity;

/**
 * Created by fangbaoyan on 2017/4/18.
 */
public class CallBackResponseEntity {
    private String VERSION;//请求报文中的参数值
    private int TYPE;//请求报文中的参数值
    private String RESPONSECODE;//响应代码
    private String RESPONSEMSG;//响应中文描述
    private String MCHNTCD;//商户号
    private String MCHNTORDERID;//商户订单号
    private String ORDERID;//富友订单号
    private String BANKCARD;//实际支付的银行卡号
    private String AMT;//交易金额
    private String SIGN;//摘要数据

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public String getRESPONSECODE() {
        return RESPONSECODE;
    }

    public void setRESPONSECODE(String RESPONSECODE) {
        this.RESPONSECODE = RESPONSECODE;
    }

    public String getRESPONSEMSG() {
        return RESPONSEMSG;
    }

    public void setRESPONSEMSG(String RESPONSEMSG) {
        this.RESPONSEMSG = RESPONSEMSG;
    }

    public String getMCHNTCD() {
        return MCHNTCD;
    }

    public void setMCHNTCD(String MCHNTCD) {
        this.MCHNTCD = MCHNTCD;
    }

    public String getMCHNTORDERID() {
        return MCHNTORDERID;
    }

    public void setMCHNTORDERID(String MCHNTORDERID) {
        this.MCHNTORDERID = MCHNTORDERID;
    }

    public String getORDERID() {
        return ORDERID;
    }

    public void setORDERID(String ORDERID) {
        this.ORDERID = ORDERID;
    }

    public String getBANKCARD() {
        return BANKCARD;
    }

    public void setBANKCARD(String BANKCARD) {
        this.BANKCARD = BANKCARD;
    }

    public String getAMT() {
        return AMT;
    }

    public void setAMT(String AMT) {
        this.AMT = AMT;
    }

    public String getSIGN() {
        return SIGN;
    }

    public void setSIGN(String SIGN) {
        this.SIGN = SIGN;
    }
}
