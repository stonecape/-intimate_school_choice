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
@RequestMapping("/login")
public class LoginController extends BaseController {
	Log logger = LogFactory.getLog(LoginController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin() {
		return new ModelAndView("login/signin");
	}
	
	@RequestMapping("/identity")
	@ResponseBody
	public Map<String, Object> identityUser(String userName, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("校验用户合法性");
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			result.put("success", false);
			result.put("msg", "参数异常");
			return result;
		}
		Result<Boolean> identityResult = userService.identifyUser(userName, password);
		
		if(identityResult == null || identityResult.getSuccess() == false) {
			result.put("success", false);
			result.put("msg", "处理异常");
			return result;
		}
		
		result.put("success", true);
		if(identityResult.getValue()) {
			result.put("report", true);
		} else {
			result.put("report", false);
		}
		
		return result;
	}
	
	@RequestMapping("/doLogin")
	public ModelAndView doLogin(UserBean user, HttpServletRequest req) {
		logger.info("do登录");
		if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
			logger.error("参数错误");
			return new ModelAndView("login/signin");
		}
		
		Result<Boolean> identityResult = userService.identifyUser(user.getUserName(), user.getPassword());
		
		if(identityResult == null || identityResult.getSuccess() == false) {
			logger.error("校验结果有误");
			return new ModelAndView("login/signin");
		}
		
		if(identityResult.getValue()) {
			// 校验通过，把user存入session
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("userName", user.getUserName());
			List<User> userList = userService.queryUser(query);
			if(userList == null || userList.size() != 1) {
				logger.error("userList == null || userList.size() != 1");
				return new ModelAndView("login/signin");
			}
			
			HttpSession session = req.getSession();
			session.setAttribute(LoginConstant.ATTR_USER, userList.get(0));
			return new ModelAndView("index");
		} else {
			logger.error("校验结果失败");
			return new ModelAndView("login/signin");
		}
	}
	
	@RequestMapping("/doLogout")
	public ModelAndView doLogout(HttpServletRequest req) {
		logger.info("==登出==");
		this.removeSessionUser(req);
		return new ModelAndView("redirect:/");
	}
}
