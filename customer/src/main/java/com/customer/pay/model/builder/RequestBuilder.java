package com.customer.pay.model.builder;

/**
 * Created by fangbaoyan on 2017/6/2.
 */
public abstract class RequestBuilder {
    private String notifyUrl;

    public abstract boolean validate();

    public abstract Object getBizContent();

    public String toString() {
        StringBuilder sb = new StringBuilder("RequestBuilder{");
        sb.append(", notifyUrl='").append(this.notifyUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public RequestBuilder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }
}
