package com.azcx9d.consumer.interceptor;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.token.TokenManagerDao;
import com.azcx9d.consumer.util.ConsumerConst;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/3/30 0030.
 * token验证拦截器，拦截用户访问的userapi接口
 */
public class CApiTokenHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    protected TokenManagerDao tokenDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if(path.matches(ConsumerConst.NO_APITOKEN_PATH)){
            return true;
        }else{
            String token = request.getHeader("token");
            String bathPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
            if(StringUtils.isBlank(token)){
                response.sendRedirect(bathPath+request.getContextPath()+"/cApi/redirect/noHeader");
                return false;
            }
            TokenModel model=tokenDao.getToken(token);
            if(!tokenDao.checkToken(model)){
                response.sendRedirect(bathPath+request.getContextPath()+"/cApi/redirect/tokenErr");
                return false;
            }
            long userId = model.getUserId();
            request.setAttribute("userId",userId);
            return true;
        }
    }
}
