package com.customer.util;


import com.customer.listener.HttpListener;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;


/**
 * Created by HuangQing on 16/2/22.
 */
public class HttpClient {
    private final static OkHttpClient client = new OkHttpClient();

    private HttpClient(){

    }
    private static final HttpClient single=new HttpClient();
    public static HttpClient getInstance(){
        return single;
    }

    /**
     * 同步get
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String syncGet(String url,Map<String,String> params) throws IOException{
    	String prefix="?t="+System.currentTimeMillis();
    	if(url.indexOf("?")>0){
    		prefix="&t="+System.currentTimeMillis();
    	}
    	if(params!=null){
    		url+=prefix;
    		for (Map.Entry<String, String> entry : params.entrySet()) {  
      		  
        		url+="&"+entry.getKey()+"="+entry.getValue();
        	  
        	}  
    	}
    	Request request = new Request.Builder()
	      .url(url)
	      .build();

	  Response response = client.newCall(request).execute();
	  return response.body().string();
    }

    /**
     * 同步post
     * @param url
     * @param params
     * @throws IOException
     */
    public void syncPost(String url,Map<String,String> params) throws IOException {
    	FormBody.Builder formBuilder=new FormBody.Builder();
    	if(params!=null){
    		for (Map.Entry<String, String> entry : params.entrySet()) {  
      		  
        		formBuilder.add(entry.getKey(), entry.getValue());  
        	  
        	}  
    	}
    	
    	RequestBody formBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).execute();
    }

    /**
     * 异步get
     * @param url
     * @param params
     * @param httpListener
     */
    public void get(String url,Map<String,String> params,final HttpListener httpListener) {
    	String prefix="?t="+System.currentTimeMillis();
    	if(url.indexOf("?")>0){
    		prefix="&t="+System.currentTimeMillis();
    	}
    	if(params!=null){
    		url+=prefix;
    		for (Map.Entry<String, String> entry : params.entrySet()) {  
      		  
        		url+="&"+entry.getKey()+"="+entry.getValue();
        	  
        	}  
    	}
    	
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpListener.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                httpListener.onSuccess(response);
            }
        });
    }

    /**
     * 异步post
     * @param url
     * @param params
     * @param httpListener
     */
    public void post(String url,Map<String,String> params,final HttpListener httpListener) {
    	
    	FormBody.Builder formBuilder=new FormBody.Builder();
    	if(params!=null){
    		for (Map.Entry<String, String> entry : params.entrySet()) {  
      		  
        		formBuilder.add(entry.getKey(), entry.getValue());  
        	  
        	}  
    	}
    	
    	RequestBody formBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpListener.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                httpListener.onSuccess(response);
            }
        });
    }
}
