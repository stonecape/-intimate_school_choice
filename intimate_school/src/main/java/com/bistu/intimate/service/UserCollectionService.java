package com.bistu.intimate.service;

import java.util.Set;

import com.bistu.intimate.common.Result;

public interface UserCollectionService {
	/**
	 * 添加收藏
	 * @param userId
	 * @param majorDetailId
	 * @return
	 */
	Result<Boolean> addNewCollection(Integer userId, Integer majorDetailId);
	
	/**
	 * 根据userId查询收藏的MajorDetailId
	 * @param userId
	 * @return
	 */
	Set<Integer> queryCollectMajorDetailIdByUserId(Integer userId);
}
