package com.bistu.intimate.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.intimate.bean.SchoolInfoQueryBean;
import com.bistu.intimate.common.Pagination;
import com.bistu.intimate.dao.SchoolInfoMapper;
import com.bistu.intimate.dto.SchoolInfo;
import com.bistu.intimate.dto.SchoolInfoExample;
import com.bistu.intimate.dto.SchoolInfoExample.Criteria;
import com.bistu.intimate.service.SchoolInfoService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

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

	public Map<String, Object> getSchoolSearchInitial() {
		logger.info("=====getSchoolSearchInitial=====");
		Map<String, Object> result = new HashMap<String, Object>();
		
		Set<String> provinceSet = new HashSet<String>();
		Set<String> areaSet = new HashSet<String>();
		Set<String> propertySet = new HashSet<String>();
		Set<String> typeSet = new HashSet<String>();
		Set<String> characterSet = new HashSet<String>();
		
		SchoolInfoExample ex = new SchoolInfoExample();
		List<SchoolInfo> list = schoolInfoMapper.selectByExample(ex);
		if(list != null && list.size() > 0) {
			for(SchoolInfo info : list) {
				if(StringUtils.isNotEmpty(info.getProvince()))
					provinceSet.add(info.getProvince());
				
				if(StringUtils.isNotEmpty(info.getArea()))
					areaSet.add(info.getArea());
				
				if(StringUtils.isNotEmpty(info.getSchoolProperty()))
					propertySet.add(info.getSchoolProperty());
				
				if(StringUtils.isNotEmpty(info.getSchoolType()))
					typeSet.add(info.getSchoolType());
				
				if(StringUtils.isNotEmpty(info.getSchoolCharacter()))
					characterSet.add(info.getSchoolCharacter());
			}
		}
		
		result.put("provinceSet", provinceSet);
		result.put("areaSet", areaSet);
		result.put("propertySet", propertySet);
		result.put("typeSet", typeSet);
		result.put("characterSet", characterSet);
		
		return result;
	}

	public Pagination<SchoolInfo> getSchoolInfoPage(SchoolInfoQueryBean queryBean) {
		logger.info("=====分页查询学校info=====");
		try {
			if(queryBean == null) {
				logger.error("参数queryBean == null");
				return null;
			}
			logger.info("queryBean->" + ToStringBuilder.reflectionToString(queryBean));
			
			SchoolInfoExample ex = new SchoolInfoExample();
			Criteria cr = ex.or();
			
			if(!StringUtils.isEmpty(queryBean.getArea())) {
				cr.andAreaEqualTo(queryBean.getArea());
			}
			
			if(!StringUtils.isEmpty(queryBean.getProvince())) {
				cr.andProvinceEqualTo(queryBean.getProvince());
			}
			
			if(!StringUtils.isEmpty(queryBean.getSchoolProperty())) {
				cr.andSchoolPropertyEqualTo(queryBean.getSchoolProperty());
			}
			
			if(!StringUtils.isEmpty(queryBean.getSchoolType())) {
				cr.andSchoolTypeEqualTo(queryBean.getSchoolType());
			}
			
			if(!StringUtils.isEmpty(queryBean.getSchoolCharacter())) {
				cr.andSchoolCharacterEqualTo(queryBean.getSchoolCharacter());
			}
			
			if(!StringUtils.isEmpty(queryBean.getSchoolName())) {
				cr.andSchoolNameLike("%" + queryBean.getSchoolName().trim() + "%");
			}
			
			PageBounds pageBounds = new PageBounds();
			pageBounds.setLimit(queryBean.getPageSize());
			pageBounds.setPage(queryBean.getPageNo());
			pageBounds.setContainsTotalCount(true);
			
			int totalCount = 0;
			
			List<SchoolInfo> resList = schoolInfoMapper.selectByExample(ex, pageBounds);
			if(resList != null && resList.size() > 0) {
				PageList<SchoolInfo> pageList = (PageList<SchoolInfo>) resList;
				totalCount = pageList.getPaginator().getTotalCount();
			}
			
			Pagination<SchoolInfo> page = 
					new Pagination<SchoolInfo>(queryBean.getPageSize(), queryBean.getPageNo(), totalCount, resList);
			logger.info("最后封装的分页返回值->" + ToStringBuilder.reflectionToString(page));
			return page;
			
		} catch(Exception e) {
			
		}
		return null;
	}

	public SchoolInfo getSchoolInfoById(int schoolId) {
		logger.info("=====getSchoolInfoById=====");
		try {
			return schoolInfoMapper.selectByPrimaryKey(schoolId);
		} catch(Exception e) {
			logger.error("getSchoolInfoById发生异常", e);
			return null;
		}
	}

}
