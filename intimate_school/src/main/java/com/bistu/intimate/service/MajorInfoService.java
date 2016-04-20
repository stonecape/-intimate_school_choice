package com.bistu.intimate.service;

import java.util.List;
import java.util.Map;

import com.bistu.intimate.bean.MajorInfoQueryBean;
import com.bistu.intimate.common.Pagination;
import com.bistu.intimate.dto.MajorInfo;

public interface MajorInfoService {
	/**
	 * 分页查询专业信息
	 * @param queryBean
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination<MajorInfo> getMajorInfoPage(MajorInfoQueryBean queryBean);
	
	/**
	 * 根据majorId查
	 * @param majorId
	 * @return
	 */
	MajorInfo getMajorInfoById(Integer majorId);
	
	/**
	 * 获取所有专业门类
	 * @return
	 */
	List<String> getAllMajorClass();
	
	/**
	 * 获取所有的专业类型
	 * @return
	 */
	List<String> getAllMajorType();
	
	/**
	 * 获取所有有效的专业名称
	 * @return
	 */
	Map<Integer, String> getAllMajorInfoSelect();
}
