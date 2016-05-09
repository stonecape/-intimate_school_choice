package com.bistu.intimate.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bistu.intimate.constant.LoginConstant;
import com.bistu.intimate.dto.User;

public class LoginInterceptor implements HandlerInterceptor {
	Log logger = LogFactory.getLog(LoginInterceptor.class);
	public String[] ignorePath ;
	public String[] adminPath;
	
	public String[] getIgnorePath() {
		return ignorePath;
	}

	public void setIgnorePath(String[] ignorePath) {
		this.ignorePath = ignorePath;
	}

	public String[] getAdminPath() {
		return adminPath;
	}

	public void setAdminPath(String[] adminPath) {
		this.adminPath = adminPath;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		logger.info("进入拦截器->requestUrl" + requestUrl);
	    
	    if(ignorePath != null && ignorePath.length > 0) {
	    	for(String ignore : ignorePath) {
		    	if(requestUrl.contains(ignore)) {
		    		return true;
		    	}
		    }
	    }
	          
	    //如果用户已经登录 放行    
	    if(request.getSession().getAttribute(LoginConstant.ATTR_USER) != null) {  
	        //更好的实现方式的使用cookie  
	    	for(String admin : adminPath) {
	    		if(requestUrl.contains(admin)) {
	    			User user = (User)request.getSession().getAttribute(LoginConstant.ATTR_USER);
		    		if(user.getIsManager() == null || user.getIsManager() != 1) {
		    			logger.error("非管理员没有进入权限");
		    			response.sendRedirect(request.getContextPath());
		    			return false;
		    		}
		    	}
	    	}
	        return true;  
	    }  
	          
	    //非法请求 即这些请求需要登录后才能访问  
	    //重定向到登录页面  
	    response.sendRedirect(request.getContextPath() + "/login/toLogin.do");  
	    return false;  
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
