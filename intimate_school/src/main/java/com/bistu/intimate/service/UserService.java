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
}
