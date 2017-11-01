package com.azcx9d.pay.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class HttpClient {
	
    public String sendHttp(String url,String content) throws IOException {
    	CloseableHttpResponse response = null;
    	 String retvlue=null;

//    		CloseableHttpClient httpclient = HttpClients.createDefault();

		    RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(30000)
				.setConnectTimeout(30000)
				.setConnectionRequestTimeout(30000)
//				.setStaleConnectionCheckEnabled(true)
				.build();

		    CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultRequestConfig(defaultRequestConfig)
				.build();

    		HttpPost httpPost = new HttpPost(url);

		    RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
				.build();

		    httpPost.setConfig(defaultRequestConfig);

    		httpPost.setHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=UTF-8");
    		StringEntity s = new StringEntity(content, "UTF-8");
    		httpPost.setEntity(s);
    		response = httpclient.execute(httpPost);
    		if (response.getStatusLine().getStatusCode() == 200) {
    			HttpEntity entity= response.getEntity();
    			//要处理该数据流是否为GZIP流
    		    retvlue= EntityUtils.toString(entity);
    		}

    	 return retvlue;
    }
   

}
