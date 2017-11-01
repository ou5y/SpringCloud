package com.azcx9d.system.interceptor;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.token.TokenManagerDao;
import com.azcx9d.system.redis.SysUserTokenManager;
import com.azcx9d.system.util.Const;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * token验证拦截器，拦截用户访问的userapi接口
 *
 */
public class SystemApiTokenHandlerInterceptor extends HandlerInterceptorAdapter{

//	@Autowired
//	protected TokenManagerDao tokenDao;

	@Autowired
	private SysUserTokenManager sysUserTokenDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");	//设置允许js跨域

		String path = request.getServletPath();
		if(path.matches(Const.NO_APITOKEN_PATH)){
			return true;
		}else{
			String token = request.getParameter("token");
			if(StringUtils.isBlank(token)){
				response.sendRedirect("/sApi/redirect/noHeader");
				return false;
			}
			TokenModel model=sysUserTokenDAO.getTokenModel(token);
			if(!sysUserTokenDAO.checkToken(model)){
				response.sendRedirect("/sApi/redirect/tokenErr");
				return false;
			}
			long userId = model.getUserId();
			request.setAttribute("userId",userId);
			return true;
		}
	}
	
}
