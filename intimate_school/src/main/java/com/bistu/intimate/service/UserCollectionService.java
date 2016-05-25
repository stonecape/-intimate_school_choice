package com.bistu.intimate.service;

import java.util.List;
import java.util.Set;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.vo.MajorDetailVo;

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
	
	/**
	 * 根据userId查VO
	 * @param userId
	 * @return
	 */
	List<MajorDetailVo> queryCollectedMajorDetailByUserId(Integer userId);
	
	/**
	 * 取消收藏
	 * @param userId
	 * @param majorDetailId
	 * @return
	 */
	Result<Boolean> deleteCollection(Integer userId, Integer majorDetailId);
}
