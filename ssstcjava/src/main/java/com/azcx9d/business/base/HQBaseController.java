package com.azcx9d.business.base;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.util.UuidUtil;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.token.TokenManagerDao;
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
public abstract class HQBaseController {

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

    //================================================================
     protected JsonResult<Object> successMsg(){
         JsonResult<Object> result = new JsonResult<Object>("");
         return result;
     }

     protected JsonResult<Object> successMsg(Object data){
         JsonResult<Object> result = new JsonResult<Object>(data);
         return result;
     }

//     protected JsonResult<Object> rtnMessage(int status,String message,Object... data){
//         JsonResult<Object> result = new JsonResult<>(status, message, data);
//         return result;
//     }

     protected JsonResult<Object> errorMsg(int status,String message){
         JsonResult<Object> result = new JsonResult<>(status, message);
         return result;
     }

    /**
     * 获取当前登录用户id
     * @return 用户id
     */
    protected int getUserId(){
         return (int)this.request.getAttribute("userId");
     }

    /**得到32位的uuid
     * @return uuid
     */
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

    /**
     * 服务端异常通用错误消息返回方法
     * @return
     */
    protected JsonResult<Object> serverExcptionMsg(){
        return this.errorMsg(500,"服务端异常");
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

}
