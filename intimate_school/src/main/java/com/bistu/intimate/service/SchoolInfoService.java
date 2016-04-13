package com.bistu.intimate.service;

import java.util.Map;

public interface SchoolInfoService {
	/**
	 * 获取所有有效的院校信息
	 * 下拉框
	 * @return
	 */
	Map<Integer, String> getAllSchoolInfoSelect();
}
