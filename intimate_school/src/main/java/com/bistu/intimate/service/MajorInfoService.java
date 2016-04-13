package com.bistu.intimate.service;

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
	 * 获取所有有效的专业信息
	 * @return
	 */
	Map<Integer, String> getAllMajorInfoSelect();
}
