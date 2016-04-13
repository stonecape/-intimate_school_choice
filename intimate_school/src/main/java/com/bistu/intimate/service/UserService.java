package com.bistu.intimate.service;

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
}
