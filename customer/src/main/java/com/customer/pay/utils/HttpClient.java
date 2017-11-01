package com.customer.pay.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class HttpClient {
	
    public String sendHttp(String url,String content) {
    	CloseableHttpResponse response = null;
    	 String retvlue=null;
    	try {
    		CloseableHttpClient httpclient = HttpClients.createDefault();
    		HttpPost httpPost = new HttpPost(url);
    		httpPost.setHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=UTF-8");
    		StringEntity s = new StringEntity(content, "UTF-8");
    		httpPost.setEntity(s);
    		response = httpclient.execute(httpPost);
    		if (response.getStatusLine().getStatusCode() == 200) {
    			HttpEntity entity= response.getEntity();
    			//要处理该数据流是否为GZIP流
    		    retvlue= EntityUtils.toString(entity);
    		   System.out.println("返回报文==="+retvlue);
    		  
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if (response != null)
    				response.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	 return retvlue;
    }
   

}
