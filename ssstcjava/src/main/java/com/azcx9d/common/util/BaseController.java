package com.azcx9d.common.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azcx9d.business.entity.ParaMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.azcx9d.business.util.UuidUtil;
import com.azcx9d.common.token.TokenManagerDao;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public abstract class BaseController {

    /**日志对象 */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static final String MESSAGES_KEY = "message";
    public static final String SUCCESS_KEY =  "success";
    public static final String OBJ_KEY =  "obj";
    public static final String ERRORS = "errors";


    public static final String TOKEN = "token";

    @Autowired
    protected TokenManagerDao tokenDao;
    
    /**
     * 返回json结果
     */
    protected HashMap<String, Object> resultMap = new  HashMap<String, Object>();

    @Autowired
	protected HttpServletRequest request;
    @Autowired
	protected HttpServletResponse response;
    
    
    
	
	
	/**
	 * 解析访问请求，返回视图
	 * @return
	 * @throws Exception
	 */
	protected ModelAndView getAutoView(String view) throws Exception {
			return new ModelAndView(view);
	}
	
    //get headers toekn
    protected String getToken() {
        return request.getHeader("token");
    }
    
    /**
     * 获取请求头里面的token
     * @return
     */
    //get request headers
    protected Map<String, String> getHeadersInfo() {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
    
    protected String get32UUID(){
        return UuidUtil.get32UUID();
    }

    /**
     * 将所有请求参数封装成Map
     * @return
     */
    protected ParaMap getParaMap() {
        return new ParaMap(this.request);
    }
    
}
