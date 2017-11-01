package com.azcx9d.agency.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.azcx9d.agency.base.Const;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.token.TokenManagerDao;

public class AgentTokenInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	protected TokenManagerDao tokenDao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if(path.matches(Const.NO_APITOKEN_PATH)){
			return true;
		}else{
			String token = request.getHeader("token");
			String bathPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			if(StringUtils.isBlank(token)){
				response.sendRedirect(bathPath+request.getContextPath()+"/agApi/redirect/noHeader");
				return false;
			}
			TokenModel model=tokenDao.getToken(token);
			if(!tokenDao.checkToken(model)){
				response.sendRedirect(bathPath+request.getContextPath()+"/agApi/redirect/tokenErr");
				return false;
			}
			long userId = model.getUserId();
			request.setAttribute("userId",userId);
			return true;
		}
	}
	
}
