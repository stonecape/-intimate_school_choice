package com.bistu.intimate.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.dao.UserMapper;
import com.bistu.intimate.dto.User;
import com.bistu.intimate.dto.UserExample;
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
			Result<Boolean> checkRes = checkUserNameIsExist(user.getUserName());
			if(!checkRes.getSuccess()) {
				logger.error("添加新用户检查用户名失败");
				result.setSuccess(false);
				return result;
			}
			
			if(checkRes.getValue()) {
				// 存在用户名
				logger.error("存在用户名");
				result.setSuccess(false);
				return result;
			} else {
				user.setCreateTime(new Date());
				user.setUpdateTime(new Date());
				int changeLine = userMapper.insertSelective(user);
				if(changeLine > 0) {
					result.setSuccess(true);
					result.setValue(user);
					return result;
				} else {
					logger.error("添加失败");
					result.setSuccess(false);
					return result;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("发生异常->", e);
			result.setSuccess(false);
			return result;
		}
		
	}

	public Result<Boolean> checkUserNameIsExist(String userName) {
		Result<Boolean> result = new Result<Boolean>();
		logger.info("查询是否存在用户名，入参userName->" + userName);
		
		try {
			UserExample ex = new UserExample();
			ex.or().andUserNameEqualTo(userName);
			
			List<User> list = userMapper.selectByExample(ex);
			
			result.setSuccess(true);
			if(list == null || list.size() <= 0) {
				result.setValue(false);
			} else {
				result.setValue(true);
			}
		} catch(Exception e) {
			logger.error("查询是否存在用户名异常->", e);
			result.setSuccess(false);
		}
		
		return result;
	}

}
