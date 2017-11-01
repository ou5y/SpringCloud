package com.customer.web;

import com.customer.entity.ParaMap;
import com.customer.util.TokenManager;
import com.customer.util.TokenManagerDao;
import com.customer.util.TokenModel;
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
 * Created by Administrator on 2017/5/8 0008.
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
    protected TokenManager tokenDao;

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

    /**
     * 将所有请求参数封装成Map
     * @return
     */
    protected ParaMap getParaMap() {
        return new ParaMap(this.request);
    }

    /**
     * 获取TokenModel
     * @return TokenModel
     */
    protected TokenModel getTokenModel() throws Exception{
        String token = request.getHeader("token");
        TokenModel model= tokenDao.getToken(token);
        return model;
    }

    protected int getUserId(){
        return (int)request.getAttribute("userId");
    }

}
