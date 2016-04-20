package com.bistu.intimate.controller;

import javax.servlet.http.HttpServletRequest;

import com.bistu.intimate.constant.LoginConstant;
import com.bistu.intimate.dto.User;

public class BaseController {
	/**
	 * 获取登陆的user
	 * @param req
	 * @return
	 */
	public User getSessionUser(HttpServletRequest req) {
		User user = (User)req.getSession().getAttribute(LoginConstant.ATTR_USER);
		return user;
	}
	
	/**
	 * 清除登录user
	 * @param req
	 */
	public void removeSessionUser(HttpServletRequest req) {
		User user = (User)req.getSession().getAttribute(LoginConstant.ATTR_USER);
		if(user != null) {
			req.getSession().removeAttribute(LoginConstant.ATTR_USER);
		}
	}
}
