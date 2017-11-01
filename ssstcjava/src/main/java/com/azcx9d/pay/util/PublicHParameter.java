package com.azcx9d.pay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/5/27.
 */
public class PublicHParameter {
    public static Map<String, String> initParameter(String userId)
    {
        Map<String, String> params =new HashMap<>();
        params.put("signType", PayConstants.SIGN_TYPE_RSA);
        params.put("charSet", PayConstants.CHARSET_UTF8);
        params.put("partnerId", "00050000");
        params.put("partnerUserId",userId);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        params.put("timestamp", dateString);
        return params;
    }
}
