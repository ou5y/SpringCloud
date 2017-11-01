package com.customer.pay.service;

import com.customer.exception.ApiException;
import com.customer.exception.CustomerException;
import com.customer.pay.utils.PayConstants;
import com.customer.pay.utils.PaySignature;
import com.customer.pay.utils.PublicHParameter;
import com.customer.util.pay.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/5/27.
 */
@Service
public class NbpayService {

    private Logger logger= LoggerFactory.getLogger(NbpayService.class);



    public String getIntoWallet(String userId)
    {
        Map<String, String> params =new HashMap<>();

        params= PublicHParameter.initParameter(userId);

        String content= PaySignature.getSignContent(params);

        String signstr=null;
        try {
            signstr = PaySignature.rsaSign(content, PayConstants.PRIVATE_KEY, PayConstants.CHARSET_UTF8,PayConstants.SIGN_TYPE_RSA);
        } catch (ApiException e) {
            logger.error(e.getErrMsg());
            throw new CustomerException(2,"服务器出错!");
        }
        try {
            content=content+"&sign="+ URLEncoder.encode(signstr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            throw new CustomerException(2,"服务器出错!");
        }

        HttpClient httpClient=new HttpClient();
        String aa=httpClient.sendHttp(PayConstants.URL, content);
        return aa;
    }


}
