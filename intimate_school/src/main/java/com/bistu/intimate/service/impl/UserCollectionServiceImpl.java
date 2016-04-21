package com.bistu.intimate.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.dao.UserCollectionMapper;
import com.bistu.intimate.dto.UserCollection;
import com.bistu.intimate.dto.UserCollectionExample;
import com.bistu.intimate.service.UserCollectionService;

@Service
public class UserCollectionServiceImpl implements UserCollectionService {
	Log logger = LogFactory.getLog(UserCollectionServiceImpl.class);
	@Autowired
	private UserCollectionMapper userCollectionMapper;
	
	public Result<Boolean> addNewCollection(Integer userId, Integer majorDetailId) {
		logger.info("===添加收藏service===userId:" + userId + ",majorDetailId:" + majorDetailId);
		Result<Boolean> result = new Result<Boolean>();
		if(userId == null || majorDetailId == null || userId <= 0 || majorDetailId <= 0) {
			logger.error("参数异常");
			result.setSuccess(false);
			return result;
		}
		
		try {
			UserCollectionExample ex = new UserCollectionExample();
			ex.or().andUserIdEqualTo(userId).andMajorDetailIdEqualTo(majorDetailId);
			List<UserCollection> currentList = userCollectionMapper.selectByExample(ex);
			
			if(currentList != null && currentList.size() > 0) {
				logger.info("已经添加");
				result.setSuccess(true);
				result.setValue(true);
				return result;
			}
			
			UserCollection userCollection = new UserCollection();
			userCollection.setCreateTime(new Date());
			userCollection.setUpdateTime(new Date());
			userCollection.setMajorDetailId(majorDetailId);
			userCollection.setUserId(userId);
			userCollection.setYn((byte)1);
			
			int line = userCollectionMapper.insertSelective(userCollection);
			result.setSuccess(true);
			if(line > 0) {
				result.setValue(true);
			} else {
				logger.error("数据库插入失败");
				result.setValue(false);
			}
			
			return result;
			
		} catch(Exception e) {
			logger.error("发生异常", e);
			result.setSuccess(false);
			return result;
		}
		
	}

}