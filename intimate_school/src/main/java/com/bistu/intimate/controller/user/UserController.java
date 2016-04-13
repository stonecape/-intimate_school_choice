package com.bistu.intimate.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.constant.DatabaseConstant;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.User;
import com.bistu.intimate.service.UserService;
import com.bistu.intimate.util.SecurityUtil;
import com.bistu.intimate.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	Log logger = LogFactory.getLog(UserController.class);
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("/signup")
	public ModelAndView signup() {
		return new ModelAndView("login/signup");
	}
	
	/**
	 * 提交注册表单
	 * @param req
	 * @return
	 */
	@RequestMapping("/addNewUser")
	public ModelAndView addNewUser(UserVo userVo) {
		try {
			logger.info("=====注册新用户=====");
			if(userVo == null) {
				logger.error("userVo==null");
				return new ModelAndView("login/signup");
			}
			logger.info("userVo->" + ToStringBuilder.reflectionToString(userVo));
			
			if(StringUtils.isEmpty(userVo.getPassword()) 
					|| StringUtils.isEmpty(userVo.getRePassword())
					|| StringUtils.isEmpty(userVo.getRealName())
					|| StringUtils.isEmpty(userVo.getSchool())
					|| StringUtils.isEmpty(userVo.getUserName())
					|| !userVo.getPassword().equals(userVo.getRePassword())) {
				logger.error("参数异常");
				return new ModelAndView("login/signup");
			}
			
			User userDto = new User();
			BeanUtils.copyProperties(userVo, userDto);
			
			String plainText = userVo.getPassword();
			String cipherText = SecurityUtil.getMd5(plainText);
			userDto.setPassword(cipherText);
			userDto.setIsManager(DatabaseConstant.isNotManager);
			
			Result<User> addResult = userService.addNewUser(userDto);
			
			if(!addResult.getSuccess()) {
				logger.error("添加用户失败");
				return new ModelAndView("/login/signup");
			}
			
			logger.info("添加用户成功");
			return new ModelAndView("index");
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("添加用户controller发生异常", e);
			return new ModelAndView("login/signup");
		}
		
	}
}
