package com.bistu.intimate.service;

import java.util.List;
import java.util.Map;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.dto.User;

public interface UserService {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	Result<User> addNewUser(User user);
	
	/**
	 * 查询是否已经存在userName
	 * @param userName
	 * @return true:已经存在 false:不存在
	 */
	Result<Boolean> checkUserNameIsExist(String userName);
	
	/**
	 * 校验登录
	 * @param userName
	 * @param password
	 * @return
	 */
	Result<Boolean> identifyUser(String userName, String password);
	
	/**
	 * 根据条件查询User
	 * @param queryMap
	 * @return
	 */
	List<User> queryUser(Map<String, Object> queryMap);
}
