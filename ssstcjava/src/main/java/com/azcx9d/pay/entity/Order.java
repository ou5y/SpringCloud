package com.azcx9d.pay.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by fangbaoyan on 2017/4/17.
 */


@XmlAccessorType(XmlAccessType.FIELD)//表示使用这个类中的 private 非静态字段作为 XML 的序列化的属性或者元素,对应属性要使用get、set方法。
@XmlRootElement(name = "ORDER")
@XmlType(propOrder = {"MCHNTCD", "TYPE", "VERSION", "LOGOTP", "MCHNTORDERID", "USERID", "AMT",
        "BANKCARD", "BACKURL", "REURL", "HOMEURL", "NAME", "IDTYPE", "IDNO",
        "REM1", "REM2", "REM3", "SIGNTP", "SIGN"
})
public class Order {


    private String MCHNTCD;//富友分配给各合作商户的唯一识别码

    private int TYPE;//交易类型

    private String VERSION; //版本号

    private int LOGOTP; //logo标志 是否隐藏支付页面富友的logo，1隐藏，0显示

    private String MCHNTORDERID;//商户订单流水号商户确保唯一

    private long USERID;//客户在商户端的唯一编号，即客户ID

    private double AMT;//订单的总金额

    private String BANKCARD;//支付的银行卡卡号

    private String BACKURL;//商户接收支付结果的后台通知地址

    private String REURL;//支付失败页面跳转到该地址进行重新支付

    private String HOMEURL;//支付成功页面跳转到该地址

    private String NAME;//用户姓名

    private int IDTYPE;//证件类型(0：身份证)

    private String IDNO;//用户身份证号

    private String REM1; //保留字段1
    private String REM2;//保留字段2
    private String REM3;//保留字段3

    private String SIGNTP;//签名方式 md5或rsa

    private String SIGN;//签名

    public String getMCHNTCD() {
        return MCHNTCD;
    }

    public void setMCHNTCD(String MCHNTCD) {
        this.MCHNTCD = MCHNTCD;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }


    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public int getLOGOTP() {
        return LOGOTP;
    }

    public void setLOGOTP(int LOGOTP) {
        this.LOGOTP = LOGOTP;
    }

    public String getMCHNTORDERID() {
        return MCHNTORDERID;
    }

    public void setMCHNTORDERID(String MCHNTORDERID) {
        this.MCHNTORDERID = MCHNTORDERID;
    }

    public long getUSERID() {
        return USERID;
    }

    public void setUSERID(long USERID) {
        this.USERID = USERID;
    }

    public double getAMT() {
        return AMT;
    }

    public void setAMT(double AMT) {
        this.AMT = AMT;
    }

    public String getBANKCARD() {
        return BANKCARD;
    }

    public void setBANKCARD(String BANKCARD) {
        this.BANKCARD = BANKCARD;
    }

    public String getBACKURL() {
        return BACKURL;
    }

    public void setBACKURL(String BACKURL) {
        this.BACKURL = BACKURL;
    }

    public String getREURL() {
        return REURL;
    }

    public void setREURL(String REURL) {
        this.REURL = REURL;
    }

    public String getHOMEURL() {
        return HOMEURL;
    }

    public void setHOMEURL(String HOMEURL) {
        this.HOMEURL = HOMEURL;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getIDTYPE() {
        return IDTYPE;
    }

    public void setIDTYPE(int IDTYPE) {
        this.IDTYPE = IDTYPE;
    }

    public String getIDNO() {
        return IDNO;
    }

    public void setIDNO(String IDNO) {
        this.IDNO = IDNO;
    }

    public String getREM1() {
        return REM1;
    }

    public void setREM1(String REM1) {
        this.REM1 = REM1;
    }

    public String getREM2() {
        return REM2;
    }

    public void setREM2(String REM2) {
        this.REM2 = REM2;
    }

    public String getREM3() {
        return REM3;
    }

    public void setREM3(String REM3) {
        this.REM3 = REM3;
    }

    public String getSIGNTP() {
        return SIGNTP;
    }

    public void setSIGNTP(String SIGNTP) {
        this.SIGNTP = SIGNTP;
    }

    public String getSIGN() {
        return SIGN;
    }

    public void setSIGN(String SIGN) {
        this.SIGN = SIGN;
    }
}
