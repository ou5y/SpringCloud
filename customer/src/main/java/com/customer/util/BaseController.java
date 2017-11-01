package com.customer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * 返回json结果
     */
    protected HashMap<String, Object> resultMap = new  HashMap<String, Object>();

    @Autowired
	protected HttpServletRequest request;
    @Autowired
	protected HttpServletResponse response;
    
    
    
	
	

    
}
