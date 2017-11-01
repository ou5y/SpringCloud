package com.customer.interceptor;


import com.customer.util.TokenManager;
import com.customer.util.TokenModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * token验证拦截器，拦截用户访问的userapi接口
 *
 */
public class ApiTokenHandlerInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(ApiTokenHandlerInterceptor.class);

	@Autowired
	protected TokenManager tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

				String token = request.getHeader("token");

				// token is not needed when debug  !! remember to comment this when deploy
				//if(token == null) return true;

				String bathPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
				if (StringUtils.isBlank(token)) {
					response.sendRedirect(bathPath + request.getContextPath() + "/api/redirect/nullToken");
					return false;
				}

				TokenModel model = tokenService.getToken(token);
				if (!tokenService.checkToken(model)) {
					response.sendRedirect(bathPath + request.getContextPath() + "/api/redirect/unmatchedToken");
					return false;
				} else {
					int userId = model.getUserId();
					int id = model.getId();
					request.setAttribute("startTime",System.currentTimeMillis());
					request.setAttribute("userId", id);
					request.setAttribute("uid", userId);
					return true;
				}

		}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		long startTime = (long) request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime = System.currentTimeMillis();
		logger.info("====================请求"+request.getRequestURL()+"处理时间:"+(endTime-startTime));

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
