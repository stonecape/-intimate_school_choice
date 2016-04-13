package com.bistu.intimate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.intimate.dao.SchoolInfoMapper;
import com.bistu.intimate.dto.SchoolInfo;
import com.bistu.intimate.dto.SchoolInfoExample;
import com.bistu.intimate.service.SchoolInfoService;

@Service
public class SchoolInfoServiceImpl implements SchoolInfoService	{
	Log logger = LogFactory.getLog(SchoolInfoServiceImpl.class);
	@Autowired
	private SchoolInfoMapper schoolInfoMapper;
	
	public Map<Integer, String> getAllSchoolInfoSelect() {
		logger.info("=====getAllSchoolInfoSelect=====");
		Map<Integer, String> returnMap = new HashMap<Integer, String>();
		SchoolInfoExample ex = new SchoolInfoExample();
		List<SchoolInfo> list = schoolInfoMapper.selectByExample(ex);
		if(list != null && list.size() > 0) {
			for(SchoolInfo info : list) {
				returnMap.put(info.getSchoolId(), info.getSchoolName());
			}
		}
		return returnMap;
	}

}
