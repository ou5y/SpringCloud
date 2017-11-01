package com.azcx9d.common.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 阿里云银行卡接口工具类
 * @author Administrator
 *
 */
public class AliyunBankUtils {
	
	private static Logger logger = LoggerFactory.getLogger(AliyunBankUtils.class);
	
	/**
	 * 服务器HOST地址
	 */
	private static final String VERIFY_HOST = "http://lundroid.market.alicloudapi.com";
	
	/**
	 * 服务器HOST地址
	 */
	private static final String QUERY_HOST = "http://cardinfo.market.alicloudapi.com";
	
	/**
	 * 实名认证地址
	 */
	private static final String VERIFY_PATH = "/lianzhuo/verifi";
	
	/**
	 * 查询银行卡信息地址
	 */
	private static final String QUERY_PATH = "/lianzhuo/querybankcard";
	
	/**
	 * 提交方式
	 */
	private static final String METHOD = "GET";
	
	/**
	 * APP_CODE
	 */
	private static final String APP_CODE = "91f05b028de94c68a1d72a387f44d5b4";
	
	/**
	 * 用户实名认证
	 * @param params
	 * @return JSONObject
	 * @throws Exception 
	 */
	public static JSONObject realNameAuthentication(Map<String,String> params) throws Exception{
		JSONObject responseObj = null;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		
		Map<String, String> querys = new HashMap<String, String>();
//	    querys.put("acct_name", URLEncoder.encode(params.get("name"), "utf-8"));
		querys.put("acct_name", params.get("name"));
	    querys.put("acct_pan", params.get("bankCardNo"));
	    if(params.get("identifyCard")!=null &&params.get("identifyCard").toString().trim().length()==18) {
			querys.put("cert_id", params.get("identifyCard"));
		}
//	    querys.put("phone_num", params.get("phone"));
	    try {
	    	HttpResponse response = HttpUtils.doGet(VERIFY_HOST, VERIFY_PATH, METHOD, headers, querys);
	    	//获取response的body
	    	String responseStr = EntityUtils.toString(response.getEntity());
	    	responseObj = JSONObject.parseObject(responseStr);
	    	logger.debug("verify："+responseStr);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	logger.equals(e.getMessage());
	    }
		return responseObj;
	}
	
	/**
	 * 查询银行卡
	 * @param bankCardNo
	 * @return JSONObject
	 * @throws Exception
	 */
	public static JSONObject queryBankcard(String bankCardNo) throws Exception{
		JSONObject responseObj = null;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		
		Map<String, String> querys = new HashMap<String, String>();
	    querys.put("bankno", bankCardNo);
	    try {
	    	HttpResponse response = HttpUtils.doGet(QUERY_HOST, QUERY_PATH, METHOD, headers, querys);
	    	//获取response的body
	    	String responseStr = EntityUtils.toString(response.getEntity());
	    	responseObj = JSONObject.parseObject(responseStr);
	    	logger.debug("queryBankCard："+responseStr);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	logger.equals(e.getMessage());
	    }
		return responseObj;
	}

}
