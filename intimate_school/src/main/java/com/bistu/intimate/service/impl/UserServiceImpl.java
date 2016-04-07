package com.bistu.intimate.service.impl;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.dao.UserMapper;
import com.bistu.intimate.dto.User;
import com.bistu.intimate.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	Log logger = LogFactory.getLog(UserServiceImpl.class);
	
	public Result<User> addNewUser(User user) {
		Result<User> result = new Result<User>();
		logger.info("添加新用户传入参数：user->" + ToStringBuilder.reflectionToString(user));
		try {
			int changeLine = userMapper.insertSelective(user);
			if(changeLine > 0) {
				result.setSuccess(true);
				result.setValue(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("发生异常->", e);
			result.setSuccess(false);
		}
		
		return result;
	}

}
