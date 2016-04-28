package com.bistu.intimate.service;

import java.util.Map;

import com.bistu.intimate.bean.SchoolInfoQueryBean;
import com.bistu.intimate.common.Pagination;
import com.bistu.intimate.dto.SchoolInfo;

public interface SchoolInfoService {
	/**
	 * 获取所有有效的院校信息
	 * 下拉框
	 * @return
	 */
	Map<Integer, String> getAllSchoolInfoSelect();
	
	/**
	 * 获取搜索页面初始化
	 * @return
	 */
	Map<String, Object> getSchoolSearchInitial();
	
	/**
	 * 分页查询
	 * @param queryBean
	 * @return
	 */
	Pagination<SchoolInfo> getSchoolInfoPage(SchoolInfoQueryBean queryBean);
}
