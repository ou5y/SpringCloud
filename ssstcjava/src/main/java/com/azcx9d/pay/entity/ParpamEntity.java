package com.azcx9d.pay.entity;

/**
 * Created by fangbaoyan on 2017/4/17.
 */
public class ParpamEntity {
    private int ENCTP;//加密标志
    private String  VERSION;//版本
    private String MCHNTCD;//商户代码
    private Order FM;

    public int getENCTP() {
        return ENCTP;
    }

    public void setENCTP(int ENCTP) {
        this.ENCTP = ENCTP;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getMCHNTCD() {
        return MCHNTCD;
    }

    public void setMCHNTCD(String MCHNTCD) {
        this.MCHNTCD = MCHNTCD;
    }

    public Order getFM() {
        return FM;
    }

    public void setFM(Order FM) {
        this.FM = FM;
    }
}
