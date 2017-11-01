package com.customer.listener;


import okhttp3.Response;

/**
 * Created by HuangQing on 16/2/22.
 */
public interface HttpListener {
    void onSuccess(Response response);
    void onFailure();
}
