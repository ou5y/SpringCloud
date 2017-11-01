package com.azcx9d.pay.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/6/9.
 */
public class NbpayTradePayResponse implements Serializable {


    private String code;

    private String msg;

    private String subCode;

    private String subMsg;
    private String body;
    private Map<String, String> params;

    public NbpayTradePayResponse() {
    }

    /** @deprecated */
    @Deprecated
    public String getErrorCode() {
        return this.getCode();
    }

    /** @deprecated */
    @Deprecated
    public void setErrorCode(String errorCode) {
        this.setCode(errorCode);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return this.subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return this.subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public boolean isSuccess() {
        return StringUtils.isEmpty(this.subCode);
    }
}
