package com.azcx9d.pay.entity.builder;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by fangbaoyan on 2017/6/9.
 */
public class NbpayTradeQueryRequestBuilder extends RequestBuilder {
    private NbpayTradeQueryRequestBuilder.BizContent bizContent = new NbpayTradeQueryRequestBuilder.BizContent();
    @Override
    public boolean validate() {
        if(StringUtils.isEmpty(this.bizContent.outTradeNo)) {
            throw new NullPointerException("out_trade_no should not be NULL!");
        } else {
            return true;
        }
    }

    @Override
    public Object getBizContent() {
        return this.getBizContent();
    }


    public String getOutTradeNo() {
        return this.bizContent.outTradeNo;
    }

    public NbpayTradeQueryRequestBuilder setOutTradeNo(String outTradeNo) {
        this.bizContent.outTradeNo = outTradeNo;
        return this;
    }

    public static class BizContent {
        @SerializedName("out_trade_no")
        private String outTradeNo;


        public BizContent() {
        }

    }
}
