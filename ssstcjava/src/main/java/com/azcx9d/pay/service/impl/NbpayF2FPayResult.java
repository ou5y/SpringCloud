package com.azcx9d.pay.service.impl;

import com.azcx9d.pay.entity.NbpayTradePayResponse;
import com.azcx9d.pay.entity.TradeStatus;
import com.azcx9d.pay.service.Result;

/**
 * Created by fangbaoyan on 2017/6/9.
 */
public class NbpayF2FPayResult implements Result {
    private TradeStatus tradeStatus;
    private NbpayTradePayResponse response;

    public NbpayF2FPayResult(NbpayTradePayResponse response) {
        this.response = response;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public void setResponse(NbpayTradePayResponse response) {
        this.response = response;
    }

    public TradeStatus getTradeStatus() {
        return this.tradeStatus;
    }

    public NbpayTradePayResponse getResponse() {
        return this.response;
    }

    public boolean isTradeSuccess() {
        return false;
    }
}
