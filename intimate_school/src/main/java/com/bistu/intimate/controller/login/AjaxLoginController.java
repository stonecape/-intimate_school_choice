package com.bistu.intimate.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bistu.intimate.bean.UserBean;
import com.bistu.intimate.common.Result;
import com.bistu.intimate.constant.LoginConstant;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.User;
import com.bistu.intimate.service.UserService;

@Controller
@RequestMapping("/ajaxLogin")
public class AjaxLoginController extends BaseController {
	Log logger = LogFactory.getLog(AjaxLoginController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/checkIsLogin")
	@ResponseBody
	public Map<String, Object> checkIsLogin(HttpServletRequest req) {
		logger.info("===检查是否登陆===");
		Map<String, Object> result = new HashMap<String, Object>();
		User user = this.getSessionUser(req);
		if(user != null) {
			result.put("isLogin", true);
		} else {
			result.put("isLogin", false);
		}
		
		return result;
	}
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public Map<String, Object> doLogin(UserBean user, HttpServletRequest req) {
		logger.info("do登录");
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
			logger.error("参数错误");
			result.put("success", false);
			return result;
		}
		
		Result<Boolean> identityResult = userService.identifyUser(user.getUserName(), user.getPassword());
		
		if(identityResult == null || identityResult.getSuccess() == false) {
			logger.error("校验结果有误");
			result.put("success", false);
			return result;
		}
		
		if(identityResult.getValue()) {
			// 校验通过，把user存入session
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("userName", user.getUserName());
			List<User> userList = userService.queryUser(query);
			if(userList == null || userList.size() != 1) {
				logger.error("userList == null || userList.size() != 1");
				result.put("success", false);
				return result;
			}
			
			HttpSession session = req.getSession();
			session.setAttribute(LoginConstant.ATTR_USER, userList.get(0));
			result.put("success", true);
			return result;
		} else {
			logger.error("校验结果失败");
			result.put("success", false);
			return result;
		}
	}
}
