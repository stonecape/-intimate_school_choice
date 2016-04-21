package com.bistu.intimate.service;

import com.bistu.intimate.common.Result;

public interface UserCollectionService {
	/**
	 * 添加收藏
	 * @param userId
	 * @param majorDetailId
	 * @return
	 */
	Result<Boolean> addNewCollection(Integer userId, Integer majorDetailId);
}
