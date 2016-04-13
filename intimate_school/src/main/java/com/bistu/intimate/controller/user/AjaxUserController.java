package com.bistu.intimate.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.service.UserService;

@Controller
@RequestMapping("/ajaxUser")
public class AjaxUserController extends BaseController {
	Log logger = LogFactory.getLog(AjaxUserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Map<String, Object> checkUserName(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String userName = req.getParameter("userName");
			if(StringUtils.isEmpty(userName)) {
				result.put("success", false);
			} else {
				Result<Boolean> isExist = userService.checkUserNameIsExist(userName.trim());
				if(isExist != null && isExist.getSuccess()) {
					result.put("success", true);
					if(isExist.getValue()) {
						// 已经存在
						result.put("isExist", "1");
					} else {
						// 不存在
						result.put("isExist", "0");
					}
				} else {
					result.put("success", false);
				}
			}
			
		} catch(Exception e) {
			result.put("success", false);
			logger.error("查询是否已经存在用户名发生异常->", e);
		}
		
		return result;
	}
	
	
}
